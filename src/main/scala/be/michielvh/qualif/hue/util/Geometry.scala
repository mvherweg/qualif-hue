package be.michielvh.qualif.hue.util

object Geometry {
  case class Point(x: Float, y: Float)

  case class Line(elementA: Point, elementB: Point) {
    if (elementA == elementB) throw new IllegalArgumentException("Cannot create Line from 2 identical points")

    val slope: Option[Float] = {
      if (elementA.x == elementB.x) None
      else Some((elementB.y - elementA.y) / (elementB.x - elementA.x))
    }

    val zero: Option[Point] = {
      slope match {
        case None => Some(Point(elementA.x, 0f))
        case Some(s) => if (s == 0) None else Some(Point(elementA.x - elementA.y / s, 0f))
      }
    }

    def contains(point: Point): Boolean = {
      if (elementA == point) true
      else {
        val newLine = Line(elementA, point)
        slope == newLine.slope && zero == newLine.zero
      }
    }

    def isAbove(point: Point): Boolean = {
      slope match {
        case None => false
        case Some(s) => zero match {
          case None => elementA.y > point.y
          case Some(z) => (point.x - z.x) * s > point.y
        }
      }
    }

    def isBelow(point: Point): Boolean = !(isAbove(point) || contains(point)) && slope.isDefined

    def separates(pointA: Point, pointB: Point): Boolean = {
      (isAbove(pointA) && isBelow(pointB)) || (isBelow(pointA) && isAbove(pointB))
    }
  }

  case class Triangle(cornerA: Point, cornerB: Point, cornerC: Point) {
    if (cornerA == cornerB || cornerB == cornerC || cornerC == cornerA) {
      throw new IllegalArgumentException("Cannot create Line with any duplicate points")
    }

    val lineAB: Line = Line(cornerA, cornerB)
    val lineBC: Line = Line(cornerB, cornerC)
    val lineCA: Line = Line(cornerC, cornerA)

    def contains(point: Point): Boolean = {
      !(lineAB.separates(point, cornerC) || lineBC.separates(point, cornerA) || lineCA.separates(point, cornerB))
    }
  }
}