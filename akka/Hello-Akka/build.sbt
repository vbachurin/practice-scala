name := "Hello-Akka"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.5.4"
libraryDependencies += "com.typesafe.akka" % "akka-agent_2.11" % "2.5.4"
libraryDependencies += "com.typesafe.akka" % "akka-testkit_2.11" % "2.5.4"
libraryDependencies += "com.typesafe.akka" % "akka-persistence_2.11" % "2.5.4"

//libraryDependencies += "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.55"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.1"

libraryDependencies += "org.iq80.leveldb"  % "leveldb" % "0.7"
libraryDependencies += "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"

resolvers += Resolver.jcenterRepo
libraryDependencies += "com.hootsuite" %% "akka-persistence-redis" % "0.6.0"

libraryDependencies += "com.typesafe.akka" % "akka-persistence-query_2.11" % "2.5.4"

libraryDependencies += "com.typesafe.akka" % "akka-remote_2.11" % "2.5.4"

libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "0.17"
