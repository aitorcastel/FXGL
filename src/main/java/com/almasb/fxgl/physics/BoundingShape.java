/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2016 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.fxgl.physics;

import javafx.geometry.BoundingBox;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Defines bounding shapes to be used for hit boxes.
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public final class BoundingShape {

    final ShapeType type;
    final Object data;
    final Dimension2D size;

    private BoundingShape(ShapeType type, Object data, Dimension2D size) {
        this.type = type;
        this.data = data;
        this.size = size;
    }

    Dimension2D getSize() {
        return size;
    }

    public static BoundingShape circle(double radius) {
        return new BoundingShape(ShapeType.CIRCLE, new Dimension2D(radius * 2, radius * 2), new Dimension2D(radius * 2, radius * 2));
    }

    public static BoundingShape box(double width, double height) {
        return new BoundingShape(ShapeType.POLYGON, new Dimension2D(width, height), new Dimension2D(width, height));
    }

    /**
     * Note: chain shape can only be used with static objects.
     *
     * @param points
     * @return
     */
    public static BoundingShape chain(Point2D... points) {
        if (points.length < 2)
            throw new IllegalArgumentException("Chain shape requires at least 2 points. Given points: " + points.length);

        double maxX = Stream.of(points)
                .mapToDouble(Point2D::getX)
                .max()
                .getAsDouble();

        double maxY = Stream.of(points)
                .mapToDouble(Point2D::getY)
                .max()
                .getAsDouble();

        return new BoundingShape(ShapeType.CHAIN, points, new Dimension2D(maxX, maxY));
    }
}
