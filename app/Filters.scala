import javax.inject._

import play.api._
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter
import play.filters.gzip.GzipFilter
import play.filters.headers.SecurityHeadersFilter
import play.api.mvc.EssentialFilter
import play.filters.csrf.CSRFFilter

@Singleton
class Filters @Inject()(
    env: Environment,
    corsFilter: CORSFilter,
    securityHeadersFilter: SecurityHeadersFilter,
    gzipFilter: GzipFilter
) extends HttpFilters {

  override val filters: Seq[EssentialFilter] = {
    // Use the example filter if we're running development mode. If
    // we're running in production or test mode then don't use any
    // filters at all.
    //        if (env.mode == Mode.Dev) Seq(restFilter) else Seq.empty

    Seq(gzipFilter, corsFilter)
  }

}
