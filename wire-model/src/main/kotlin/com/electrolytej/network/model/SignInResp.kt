// Code generated by Wire protocol buffer compiler, do not edit.
// Source: com.electrolytej.network.model.SignInResp in 17121212.proto
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

public class SignInResp(
  unknownFields: ByteString = ByteString.EMPTY,
) : Message<SignInResp, Nothing>(ADAPTER, unknownFields) {
  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN,
  )
  override fun newBuilder(): Nothing = throw AssertionError("Builders are deprecated and only available in a javaInterop build; see https://square.github.io/wire/wire_compiler/#kotlin")

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is SignInResp) return false
    if (unknownFields != other.unknownFields) return false
    return true
  }

  override fun hashCode(): Int = unknownFields.hashCode()

  override fun toString(): String = "SignInResp{}"

  public fun copy(unknownFields: ByteString = this.unknownFields): SignInResp = SignInResp(unknownFields)

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<SignInResp> = object : ProtoAdapter<SignInResp>(
      FieldEncoding.LENGTH_DELIMITED, 
      SignInResp::class, 
      "type.googleapis.com/com.electrolytej.network.model.SignInResp", 
      PROTO_3, 
      null, 
      "17121212.proto"
    ) {
      override fun encodedSize(`value`: SignInResp): Int {
        var size = value.unknownFields.size
        return size
      }

      override fun encode(writer: ProtoWriter, `value`: SignInResp) {
        writer.writeBytes(value.unknownFields)
      }

      override fun encode(writer: ReverseProtoWriter, `value`: SignInResp) {
        writer.writeBytes(value.unknownFields)
      }

      override fun decode(reader: ProtoReader): SignInResp {
        val unknownFields = reader.forEachTag(reader::readUnknownField)
        return SignInResp(
          unknownFields = unknownFields
        )
      }

      override fun redact(`value`: SignInResp): SignInResp = value.copy(
        unknownFields = ByteString.EMPTY
      )
    }

    private const val serialVersionUID: Long = 0L
  }
}
