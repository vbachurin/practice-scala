package com.octopus

import com.softwaremill.sttp.Uri

// Represents one line in the input file
trait Ingredient {
  val uri: Uri
  val output: String
}

// Entity that will be downloaded at full speed
case class Food(uri: Uri, output: String) extends Ingredient

// Entity that will be downloaded at limited speed (limit defined by 'carefulness')
case class Liquid(uri: Uri, output: String)(implicit carefulness: String)
    extends Ingredient {
  val limit = limitHelper(carefulness)
}

// These two duplicate Food and Liquid, for testing purposes
case class TestFood(uri: Uri, output: String) extends Ingredient
case class TestLiquid(uri: Uri, output: String)(implicit carefulness: String)
    extends Ingredient {
  val limit = limitHelper(carefulness)
}

