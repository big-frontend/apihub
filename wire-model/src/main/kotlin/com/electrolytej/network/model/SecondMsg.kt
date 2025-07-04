// Code generated by Wire protocol buffer compiler, do not edit.
// Source: com.electrolytej.network.model.SecondMsg in Profile1.proto
@file:Suppress(
  "DEPRECATION",
  "RUNTIME_ANNOTATION_NOT_SUPPORTED",
)

package com.electrolytej.network.model

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.ReverseProtoWriter
import com.squareup.wire.Syntax.PROTO_3
import com.squareup.wire.WireField
import com.squareup.wire.`internal`.JvmField
import kotlin.Any
import kotlin.AssertionError
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.Long
import kotlin.Nothing
import kotlin.String
import kotlin.Suppress
import okio.ByteString

public class SecondMsg(
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.ProtoAdapter#INT32",
    label = WireField.Label.OMIT_IDENTITY,
    schemaIndex = 0,
  )
  public val blah: Int = 0,
  unknownFields: ByteString = ByteString.EMPTY,
) : Message<SecondMsg, Nothing>(ADAPTER, unknownFields) {
  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN,
  )
  override fun newBuilder(): Nothing = throw AssertionError("Builders are deprecated and only available in a javaInterop build; see https://square.github.io/wire/wire_compiler/#kotlin")

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is SecondMsg) return false
    if (unknownFields != other.unknownFields) return false
    if (blah != other.blah) return false
    return true
  }

  override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + blah.hashCode()
      super.hashCode = result
    }
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    result += """blah=$blah"""
    return result.joinToString(prefix = "SecondMsg{", separator = ", ", postfix = "}")
  }

  public fun copy(blah: Int = this.blah, unknownFields: ByteString = this.unknownFields): SecondMsg = SecondMsg(blah, unknownFields)

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<SecondMsg> = object : ProtoAdapter<SecondMsg>(
      FieldEncoding.LENGTH_DELIMITED, 
      SecondMsg::class, 
      "type.googleapis.com/com.electrolytej.network.model.SecondMsg", 
      PROTO_3, 
      null, 
      "Profile1.proto"
    ) {
      override fun encodedSize(`value`: SecondMsg): Int {
        var size = value.unknownFields.size
        if (value.blah != 0) {
          size += ProtoAdapter.INT32.encodedSizeWithTag(1, value.blah)
        }
        return size
      }

      override fun encode(writer: ProtoWriter, `value`: SecondMsg) {
        if (value.blah != 0) {
          ProtoAdapter.INT32.encodeWithTag(writer, 1, value.blah)
        }
        writer.writeBytes(value.unknownFields)
      }

      override fun encode(writer: ReverseProtoWriter, `value`: SecondMsg) {
        writer.writeBytes(value.unknownFields)
        if (value.blah != 0) {
          ProtoAdapter.INT32.encodeWithTag(writer, 1, value.blah)
        }
      }

      override fun decode(reader: ProtoReader): SecondMsg {
        var blah: Int = 0
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> blah = ProtoAdapter.INT32.decode(reader)
            else -> reader.readUnknownField(tag)
          }
        }
        return SecondMsg(
          blah = blah,
          unknownFields = unknownFields
        )
      }

      override fun redact(`value`: SecondMsg): SecondMsg = value.copy(
        unknownFields = ByteString.EMPTY
      )
    }

    private const val serialVersionUID: Long = 0L
  }
}
