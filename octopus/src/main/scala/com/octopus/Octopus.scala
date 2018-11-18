package com.octopus

import java.io.File

object Octopus extends App {

  val parser =
    new scopt.OptionParser[OctopusArgValues]("java -jar octopus.jar") {
      head("Octopus cooking meal simulator :)")

      opt[Int]('n', "number-of-limbs")
        .action((x, argValues) => argValues.copy(numberOfLimbs = x))
        .validate(x =>
          if (x > 0) success
          else failure("Number of limbs must be > 0"))
        .text("the number of downloading threads (1,2,3,4...)")

      opt[String]('l', "carefulness")
        .action((x, argValues) => argValues.copy(carefulness = x))
        .validate { x =>
          val regex = "^[0-9]+[km]?$"
          if (x.matches(regex)) success
          else failure(s"Carefulness value must match regex $regex")
        }
        .text(
          s"download limit for eligible files, bytes/second with suffixes (k=1024, m=1024*1024). Default: 1m")
        .withFallback(() => "1m")

      opt[File]('i', "ingredients")
        .required()
        .valueName("<file>")
        .action((x, argValues) => argValues.copy(ingredients = x))
        .validate {
          case file if file.exists() && file.isFile => success
          case _                                    => failure("Ingredients file doesn't exist or is a directory")
        }
        .text("input file with links")

      opt[File]('o', "meals")
        .valueName("<file>")
        .action((x, argValues) => argValues.copy(meals = x))
        .validate {
          case dir if dir.isFile => failure("Meals must be a directory")
          case dir               => createDirIfNotExists(dir.getName); success
        }
        .text("output folder for downloaded files. Default: breakfast_folder")
        .withFallback(() => createDirIfNotExists("breakfast_folder"))
    }

  parser.parse(args, OctopusArgValues()) match {
    case Some(values: OctopusArgValues) =>
      new Grabber(values).prepareMeal()
    case None =>
      println("Error: Couldn't parse arguments. Check details above")
  }

  private def createDirIfNotExists(name: String) = {
    val dir = new File(name)
    if (!dir.exists()) {
      dir.mkdir()
      println(s"Directory '$name' was created for downloaded files")
    }
    dir
  }
}

/**
  * Contains command line argument values
  * @param numberOfLimbs stands for simultaneous download threads
  * @param carefulness stands for download rate limit for files configured in liquid-marker configuration parameter
  * @param ingredients file containing pairs of URI and resulting file name
  * @param meals directory where all downloaded files will be stored
  */
case class OctopusArgValues(numberOfLimbs: Int = -1,
                            carefulness: String = "",
                            ingredients: File = new File("."),
                            meals: File = new File("."))
