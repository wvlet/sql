package wvlet.sql

//import scala.language.experimental.macros
//import scala.reflect.macros.{blackbox => sm}
//
///**
//  *
//  */
//object SQLMacros {
//
//  def filter(c: sm.Context)(f: c.Tree) : c.Tree = {
//    import c.universe._
//    new Helper[c.type](c).filter(f)
//  }
//
//  private[sql] class Helper[C <: sm.Context](val c: C) {
//    import c.universe._
//
//    def filter(f:Tree): Tree = {
//      q""
//    }
//  }
//}
