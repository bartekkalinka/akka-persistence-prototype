package edu

import akka.serialization.SerializerWithStringManifest

class Event1Serializer extends SerializerWithStringManifest {

  import java.io.ByteArrayOutputStream
  import com.sksamuel.avro4s.{ AvroInputStream, AvroOutputStream, FromRecord, SchemaFor, ToRecord }

  override def identifier: Int = 1843123541

  implicit val schemaFor = SchemaFor[Event1]
  implicit val fromRecord = FromRecord[Event1]
  implicit val toRecord = ToRecord[Event1]

  override def manifest(obj: AnyRef): String = obj.getClass.getName

  override def toBinary(o: AnyRef): Array[Byte] = {
    val output = new ByteArrayOutputStream
    val avro = AvroOutputStream.binary(output)
    avro.write(o.asInstanceOf[Event1])
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

