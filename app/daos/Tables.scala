package daos

import com.github.aselab.activerecord.{ActiveRecordTables, PlaySupport}
import com.github.aselab.activerecord.dsl._

/**
  * Created by liangliao on 4/14/16.
  */
object Tables extends ActiveRecordTables with PlaySupport {

  val photo: _root_.com.github.aselab.activerecord.dsl.Table[Photo] = table[Photo]("photo")
  val system: _root_.com.github.aselab.activerecord.dsl.Table[System] = table[System]("system")
}
