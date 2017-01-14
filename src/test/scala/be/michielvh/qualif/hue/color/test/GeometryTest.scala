package be.michielvh.qualif.hue.color.test

import be.michielvh.qualif.hue.util.Geometry.{Line, Point, Triangle}
import org.scalatest.PropSpec

class GeometryTest extends PropSpec {
  property("A non-horizontal line has a zero Point representing the intercept with the x-axis") {
    assert(Line(Point(1, 1), Point(2, 2)).zero.isDefined)
    assert(Line(Point(1, 1), Point(2, 2)).zero.contains(Point(0, 0)))
    assert(Line(Point(1, 0), Point(2, 4)).zero.contains(Point(1, 0)))
    assert(Line(Point(2, 4), Point(1, 0)).zero.contains(Point(1, 0)))
    assert(Line(Point(1, 4), Point(2, 0)).zero.contains(Point(2, 0)))
    assert(Line(Point(1, 1), Point(1, 2)).zero.contains(Point(1, 0)))
  }

  property("A horizontal line, has no zero Point defined") {
    assert(Line(Point(1, 1), Point(2, 1)).zero.isEmpty)
  }

  property("A non-vertical line, has a slope, representing the increase in y per unit of increase in x on the line") {
    assert(Line(Point(1, 1), Point(2, 2)).slope.isDefined)
    assert(Line(Point(1, 1), Point(2, 2)).slope.contains(1f))
    assert(Line(Point(1, 0), Point(2, 4)).slope.contains(4f))
    assert(Line(Point(2, 4), Point(1, 0)).slope.contains(4f))
    assert(Line(Point(1, 4), Point(2, 0)).slope.contains(-4f))
    assert(Line(Point(0, 2), Point(1, 2)).slope.contains(0))
  }

  property("A vertical, has no slope defined") {
    assert(Line(Point(1, 1), Point(1, 2)).slope.isEmpty)
  }

  property("A line initiation with 2 identical points should throw an IllegalArgumentException") {
    assertThrows[IllegalArgumentException] {
      val point = Point(1, 1)
      Line(point, point)
    }
  }

  property("Calling isAbove on a climbing line returns whether it is above the provided point or not") {
    val climbing = Line(Point(0, 0), Point(1, 2))

    assert(climbing.isAbove(Point(1, 1)))
    assert(!climbing.isAbove(Point(1, 3)))
    assert(!climbing.isAbove(Point(1, 2)))

    assert(climbing.isAbove(Point(2, 3)))
    assert(!climbing.isAbove(Point(2, 5)))
    assert(!climbing.isAbove(Point(2, 4)))
  }

  property("Calling isAbove on a horizontal line returns whether it is above the provided point or not") {
    val horizontal = Line(Point(1, 2), Point(2, 2))

    assert(!horizontal.isAbove(Point(1, 3)))
    assert(horizontal.isAbove(Point(1, 1)))
    assert(!horizontal.isAbove(Point(1, 2)))

    assert(!horizontal.isAbove(Point(2, 3)))
    assert(horizontal.isAbove(Point(2, 1)))
    assert(!horizontal.isAbove(Point(2, 2)))
  }

  property("Calling isAbove on a vertical line always returns false") {
    val vertical = Line(Point(1, 1), Point(1, 2))

    assert(!vertical.isAbove(Point(0, 0)))
    assert(!vertical.isAbove(Point(0, 1)))
    assert(!vertical.isAbove(Point(0, 2)))
    assert(!vertical.isAbove(Point(0, 3)))

    assert(!vertical.isAbove(Point(1, 0)))
    assert(!vertical.isAbove(Point(1, 1)))
    assert(!vertical.isAbove(Point(1, 2)))
    assert(!vertical.isAbove(Point(1, 3)))

    assert(!vertical.isAbove(Point(2, 0)))
    assert(!vertical.isAbove(Point(2, 1)))
    assert(!vertical.isAbove(Point(2, 2)))
    assert(!vertical.isAbove(Point(2, 3)))
  }

  property("Calling isBelow on a climbing line returns whether it is below the provided point or not") {
    val climbing = Line(Point(0, 0), Point(1, 2))

    assert(!climbing.isBelow(Point(1, 1)))
    assert(climbing.isBelow(Point(1, 3)))
    assert(!climbing.isBelow(Point(1, 2)))

    assert(!climbing.isBelow(Point(2, 3)))
    assert(climbing.isBelow(Point(2, 5)))
    assert(!climbing.isBelow(Point(2, 4)))
  }

  property("Calling isBelow on a horizontal line returns whether it is below the provided point or not") {
    val horizontal = Line(Point(1, 2), Point(2, 2))

    assert(horizontal.isBelow(Point(1, 3)))
    assert(!horizontal.isBelow(Point(1, 1)))
    assert(!horizontal.isBelow(Point(1, 2)))

    assert(horizontal.isBelow(Point(2, 3)))
    assert(!horizontal.isBelow(Point(2, 1)))
    assert(!horizontal.isBelow(Point(2, 2)))
  }

  property("Calling isBelow on a vertical line always returns false") {
    val vertical = Line(Point(1, 1), Point(1, 2))

    assert(!vertical.isBelow(Point(0, 0)))
    assert(!vertical.isBelow(Point(0, 1)))
    assert(!vertical.isBelow(Point(0, 2)))
    assert(!vertical.isBelow(Point(0, 3)))

    assert(!vertical.isBelow(Point(1, 0)))
    assert(!vertical.isBelow(Point(1, 1)))
    assert(!vertical.isBelow(Point(1, 2)))
    assert(!vertical.isBelow(Point(1, 3)))

    assert(!vertical.isBelow(Point(2, 0)))
    assert(!vertical.isBelow(Point(2, 1)))
    assert(!vertical.isBelow(Point(2, 2)))
    assert(!vertical.isBelow(Point(2, 3)))
  }

  property("A triangle initiation with 2 or 3 identical points should throw an IllegalArgumentException") {
    val pointA = Point(1, 1)
    val pointB = Point(2, 2)

    assertThrows[IllegalArgumentException] {
      Triangle(pointA, pointA, pointA)
    }
    assertThrows[IllegalArgumentException] {
      Triangle(pointA, pointA, pointB)
    }
    assertThrows[IllegalArgumentException] {
      Triangle(pointA, pointB, pointA)
    }
    assertThrows[IllegalArgumentException] {
      Triangle(pointB, pointA, pointA)
    }
  }

  property("Calling contains on a triangle with 3 diagonal lines returns whether the point is in or on the triangle") {
    val triangle = Triangle(Point(1, 2), Point(3, 3), Point(4, 4))

    assert(triangle.contains(Point(3.5f, 3.5f)))
    assert(triangle.contains(Point(4, 4)))
    assert(triangle.contains(Point(3, 3.1f)))

    assert(!triangle.contains(Point(2, 4)))
    assert(!triangle.contains(Point(4, 3)))
    assert(!triangle.contains(Point(0.5f, 0.5f)))
    assert(!triangle.contains(Point(3.5f, 2.5f)))
    assert(!triangle.contains(Point(5, 5)))
  }

  property("Calling contains on a triangle with 1 horizontal line returns whether the point is in or on the triangle") {
    val triangle = Triangle(Point(1, 2), Point(3, 2), Point(4, 4))

    assert(triangle.contains(Point(2, 2)))
    assert(triangle.contains(Point(4, 4)))
    assert(triangle.contains(Point(3, 3)))

    assert(!triangle.contains(Point(2, 4)))
    assert(!triangle.contains(Point(4, 3)))
    assert(!triangle.contains(Point(0.5f, 0.5f)))
    assert(!triangle.contains(Point(2, 1)))
    assert(!triangle.contains(Point(5, 2)))
  }

  property("Calling contains on a triangle with 1 vertical line returns whether the point is in or on the triangle") {
    val triangle = Triangle(Point(1, 2), Point(4, 3), Point(4, 4))

    assert(triangle.contains(Point(3, 3)))
    assert(triangle.contains(Point(4, 4)))
    assert(triangle.contains(Point(4, 3.5f)))

    assert(!triangle.contains(Point(2, 4)))
    assert(!triangle.contains(Point(5, 3)))
    assert(!triangle.contains(Point(0.5f, 0.5f)))
    assert(!triangle.contains(Point(4, 2)))
  }
}