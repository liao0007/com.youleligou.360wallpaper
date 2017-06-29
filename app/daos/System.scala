package daos

import com.github.aselab.activerecord.{ActiveRecordCompanion, PlayFormSupport}


/**
  * Created by liangliao on 27/6/17.
  */
case class System(
                   override val id: Long,
                   key: String,
                   value: String,
                   override protected val occVersionNumber: Int
                 ) extends ActiveRecord


object System extends ActiveRecordCompanion[System] with PlayFormSupport[System]