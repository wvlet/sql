package wvlet.sql


object Schema {
  sealed trait ColumnType
  sealed trait PrimitiveType extends ColumnType
  sealed trait StructureType extends ColumnType

  case object NIL extends PrimitiveType
  case object ANY extends PrimitiveType
  case object INTEGER extends PrimitiveType
  case object FLOAT extends PrimitiveType
  case object BOOLEAN extends PrimitiveType
  case object STRING extends PrimitiveType
  case object TIMESTAMP extends PrimitiveType
  case object BINARY extends PrimitiveType
  case object JSON extends PrimitiveType
  case class ARRAY(elemType: ColumnType) extends StructureType
  case class MAP(keyType: ColumnType, valueType: ColumnType) extends StructureType
  case class RECORD(column: Seq[Column]) extends StructureType

  object ColumnType {
    def unapply(s: String): Option[ColumnType] = {
      val tpe = s match {
        case "varchar" => STRING
        case "string" => STRING
        case "bigint" => INTEGER
        case "double" => FLOAT
        case "float" => FLOAT
        case "json" => STRING // TODO jse JSON type
        case _ => STRING // TODO support more type
      }
      Some(tpe)
    }
  }
}

case class Column(
  // 0-origin index
  index:Int,
  name: String,
  dataType: Schema.ColumnType
) {
  override def toString = s"${index}:${name} ${dataType}"
}

case class Schema(name: String, column: Seq[Column]) {
  override def toString : String = s"$name(${column.mkString(", ")})"
  private lazy val columnIdx: Map[Column, Int] = column.zipWithIndex.toMap[Column, Int]

  def size: Int = column.size

  /**
    * @param index 0-origin index
    * @return
    */
  def columnType(index: Int) = column(index)

  /**
    * Return the column index
    *
    * @param column
    * @return
    */
  def columnIndex(column: Column) = columnIdx(column)
}
