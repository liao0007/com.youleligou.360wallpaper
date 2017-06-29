package controllers.api

import com.github.aselab.activerecord.dsl._
import com.google.inject.Inject
import daos.Photo
import models.ModelPager
import play.api.Logger
import play.api.libs.json.{JsArray, Json}
import play.api.libs.ws.{WSClient, WSRequest}
import play.api.mvc.{Action, AnyContent, InjectedController}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

/**
  * Created by liangliao on 27/6/17.
  */
class PhotoController @Inject()(ws: WSClient) extends InjectedController {


  def update: Action[AnyContent] = Action.async {

    val request: WSRequest = ws.url("http://api.unsplash.com/photos/")

    val system: daos.System = daos.System.findBy("key", "page").get

    request.addHttpHeaders("Accept" -> "application/json")
      .addQueryStringParameters(
        "client_id" -> "395005f5b2b2da243b35408e946ff70212a51767673edefc795c95e540231a3f",
        "page" -> system.value,
        "per_page" -> "30").get() map { response =>


      system.copy(value = (system.value.toInt + 1).toString).update

      Json.parse(response.body) match {
        case JsArray(value) =>
          value map { item =>
            item
              .validate[Photo]
              .fold({ reason =>
                Logger.warn("parse photo failed, " + reason.toString)
                None
              }, { photo =>
                Try(photo.copy(url = photo.url.replace("https://images.unsplash.com", "")).create)
              })
          }
        case _ => Seq.empty[Photo]
      }

      Ok
    }
  }

  // Action and parse now use the injected components
  def index(pager: ModelPager): Action[AnyContent] = Action {

    val ModelPager(page, size) = pager
    val photos = Photo.page(page * size, size).orderBy("id", "DESC").toList

    Ok(Json.toJson(photos))


  }

}
