package com.hit.test;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = 0;
        double area = 0;
        while(true) {
            int flag = input.nextInt();
            if(flag == 1) {
                num++;
                double r = input.nextInt();
                input.nextInt();
                Circle c = new Circle(r,input.next(),input.nextBoolean());
                System.out.printf("Circle area is:%f color is:%s\n",c.getArea(),c.getColor());
                area += c.getArea();
            }else if (flag == 2) {
                num++;
                double a = input.nextInt();
                double b = input.nextInt();
                Rectangle rt = new Rectangle(input.next(),input.nextBoolean(), a, b);
                area += rt.getArea();
                System.out.printf("Rectangle area is:%f color is:%s\n",rt.getArea(),rt.getColor());
            }else {
                break;
            }
        }
        System.out.println("----------");
        System.out.printf("%d objects, total area are: %f",num,area);
    }
}
abstract class GeometricObject{
    private String color;
    private boolean filled;
    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
    public GeometricObject() {
        this.color = "white";
        this.filled = true;
    }
    abstract double getArea();
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
class Circle extends GeometricObject{
    private double r;
    Circle(double r, String color, boolean filled) {
        super(color, filled);
        this.r = r;
    }
    public Circle(double r) {
        this.r = r;
    }

    public double getArea() {
        return Math.PI * r * r;
    }
    public String toString() {
        return "Circle radius = " + this.r;
    }
}
class Rectangle extends GeometricObject{
    private double a;
    private double b;

    public Rectangle(String color, boolean filled, double a, double b) {
        super(color, filled);
        this.a = a;
        this.b = b;
    }
    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getArea() {
        return a * b;
    }



}
