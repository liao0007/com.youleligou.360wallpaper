package daos

import com.github.aselab.activerecord.{ActiveRecordCompanion, PlayFormSupport}
import org.joda.time.DateTime
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by liangliao on 27/6/17.
  */
case class Photo(
                  photoId: String,
                  createdAt: DateTime,
                  updatedAt: DateTime,
                  width: Int,
                  height: Int,
                  color: String,
                  likes: Int,
                  likedByUser: Boolean,
                  url: String
                ) extends ActiveRecord


object Photo extends ActiveRecordCompanion[Photo] with PlayFormSupport[Photo] {
  val pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
  implicit val dateFormat = Format[DateTime](JodaReads.jodaDateReads(pattern), JodaWrites.jodaDateWrites(pattern))

  implicit val jsonRead: Reads[Photo] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "created_at").read[DateTime] and
      (JsPath \ "updated_at").read[DateTime] and
      (JsPath \ "width").read[Int] and
      (JsPath \ "height").read[Int] and
      (JsPath \ "color").read[String] and
      (JsPath \ "likes").read[Int] and
      (JsPath \ "liked_by_user").read[Boolean] and
      (JsPath \ "urls" \ "raw").read[String]
    ) (Photo.apply _)

  implicit val jsonWrite: OWrites[Photo] = Json.writes[Photo]
}