package daos

import com.github.aselab.activerecord.Timestamps
import com.github.aselab.activerecord.inner.{CRUDable, Optimistic}

/**
  * Created by liangliao on 17/7/16.
  */
abstract class ActiveRecord
  extends com.github.aselab.activerecord.ActiveRecord
    with Optimistic

trait IterableAttribute[T] extends Seq[T] {
  protected def all: Seq[T]
  override def length: Int = all.length
  override def apply(idx: Int): T = all(idx)
  override def iterator: Iterator[T] = all.toIterator
}