package wvlet.sql



// TODO migrate this definition to wvlet-core
trait SQL[R] {
  def schema: Schema

}


class SQLSeq[R](val sql:String, val schema:Schema) extends SQL[R] {
  //import SQLMacros._
  //def filter(f: R => Boolean): SQL[R] = macro SQLMacros
}

//class SQLFilter[R](sql:SQL[R], )
