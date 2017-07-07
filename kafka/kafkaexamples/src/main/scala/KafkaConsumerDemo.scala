import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.{StringDeserializer}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by vladyslav.bachurin on 7/5/2017.
  */
object KafkaConsumerDemo {
  def main(args: Array[String]): Unit = {
    var properties = new Properties
    properties.setProperty("bootstrap.servers", "192.168.99.100:9092")
    properties = ConsumerConfig.addDeserializerToConfig(properties, new StringDeserializer, new StringDeserializer)

    properties.setProperty("group.id", "test")
    properties.setProperty("eanble.auto.commit", "true")
    properties.setProperty("auto.commit.interval.ms", "1000")
    properties.setProperty("auto.offset.reset", "earliest")

    println(properties)

    val kafkaConsumer = new KafkaConsumer[String, String](properties)
    import collection.JavaConverters._
    kafkaConsumer.subscribe(ArrayBuffer("first_topic").asJava)

    while (true) {
      val consumerRecords = kafkaConsumer.poll(100)
      consumerRecords.forEach(record =>
        println(
          s"Partition: ${record.partition()}, Offset: ${record.offset()}," +
          s"Key: ${record.key()}, Value: ${record.value()}")
      )
    }

  }

}
