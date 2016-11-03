package edu

import akka.serialization.SerializerWithStringManifest

class Event2Serializer extends SerializerWithStringManifest {

  import java.io.ByteArrayOutputStream

  import com.sksamuel.avro4s._

  override def identifier: Int = 1843123540

  implicit val schemaFor = SchemaFor[Event2]
  implicit val fromRecord = FromRecord[Event2]
  implicit val toRecord = ToRecord[Event2]

  override def manifest(obj: AnyRef): String = obj.getClass.getName

  override def toBinary(o: AnyRef): Array[Byte] = {
    val output = new ByteArrayOutputStream
    val avro = AvroOutputStream.binary(output)
    avro.write(o.asInstanceOf[Event2])
    avro.close()
    output.toByteArray
  }

  override def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = {
    val input = AvroInputStream.binary(bytes)
    val events = input.iterator.toList
    input.close()
    events.head
  }
}

