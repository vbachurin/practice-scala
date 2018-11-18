
# HTTP Octopus

## Description
Command line utility which downloads the links specified in the input files and stores them with the specified name in the output directory.
Supports multithreading and (for the links containing the specific string) download speed limitation/throttling.
The file name eligible for throttling is configurable through the application.conf file.


## Obtaining the runnable
`sbt assembly` creates the `octopus.jar` file inside `target/scala-2.12/` with all necessary dependencies built-in.
```
MacBook-Pro:octopus$ sbt assembly
MacBook-Pro:octopus$ cd target/scala-2.12
MacBook-Pro:scala-2.12$ ls octopus.jar
```
## Usage
```
Usage: java -jar octopus.jar [options]

  -n, --number-of-limbs <value>
                           the number of downloading threads (1,2,3,4...)
  -l, --carefulness <value>
                           download limit for eligible files, bytes/second with suffixes (k=1024, m=1024*1024). Default: 1m
  -i, --ingredients <file>
                           input file with links
  -o, --meals <file>       output folder for downloaded files. Default: breakfast_folder
```

## Example
```
java -jar octopus.jar -n 8 -l 1024k -o breakfast_folder -i ingredients.txt
```

The above command will download the files specified in the `ingredients.txt` in 8 simultaneous threads.
By default throttling (`-l 1024k`) will be applied only to those links, whose name equals to `water`.
The rest of files will be downloaded at full speed.
All files will be saved to directory `breakfast_folder`, which will be created if doesn't exist.

## Input file example (with working links)
```
http://www.ovh.net/files/1Mio.dat coffee.jpg
http://www.ovh.net/files/10Mio.dat scrambled_eggs.zip
http://www.ovh.net/files/1Mio.dat tea.jpg
http://www.ovh.net/files/1Mio.dat coffee2.jpg
http://www.ovh.net/files/10Mio.dat sandwich.bin
http://www.ovh.net/files/10Mio.dat sandwich2.bin
http://www.ovh.net/files/10Mio.dat sandwich3.bin
http://www.ovh.net/files/10Mio.dat sandwich4.bin
http://www.ovh.net/files/10Mio.dat sandwich5.bin
http://www.ovh.net/files/10Mio.dat sandwich6.bin
http://www.ovh.net/files/10Mio.dat sandwich7.bin
```

(`1Mio` is one more file name that is eligible for throttling by default)

## Implementation notes
The following libraries were used besides Scala 2.12:
* `scopt` for command line attributes parsing
* `sttp` as a convenient HTTP client
* `akka-http` as a backend for sttp client
* `akka-streams` for throttling and writing into files
* `sbt-assembly` for creating a JAR file with all dependencies inside ('fat JAR')

Introduced entities TestFood and TestLiquid along with main ones Food and Liquid.
This was done in order to be able to test the appliccation against real files.
