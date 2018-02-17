// See LICENSE for license details.

package chisel3.core

import chisel3.internal.firrtl._

trait Literal[+T <: Data] {
  /** Converts this Literal to its Data equivalent with a Literal binding.
    */
  def toData: T
}

object Literal {
  implicit def toLiteral[T <: Data](lit: Literal[T]): T = lit.toData
}

trait BitsLiteral extends Literal[Bits] {
  /** Returns the literal value as a Scala Int. Throws an exception if it will not fit.
   */
  def asInt: Int

  /** Returns the literal value as a Scala BigInt.
   */
  def asBigInt: BigInt
}

class UIntLiteral(litVal: ULit) extends BitsLiteral with Literal[UInt] {
  override def asInt: Int
  override def asBigInt: BigInt

  override def toData: UInt = {
    val data = UInt()

    data.bind(LitBinding())
    data
  }
}
