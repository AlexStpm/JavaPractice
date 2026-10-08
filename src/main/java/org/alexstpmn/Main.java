package org.alexstpmn;

public class Main {
    static void main() {
        MyStringBuilder myStrB = new MyStringBuilder("Hello");
        System.out.println(myStrB); //Hello
        myStrB.append("123");
        System.out.println(myStrB); //Hello123
        myStrB.undo();
        System.out.println(myStrB); //Hello
        myStrB.delete(0, 3);
        System.out.println(myStrB); //lo
        myStrB.undo();
        System.out.println(myStrB); //Hello
        myStrB.append(" one");
        myStrB.append(" two");
        myStrB.append(" three");
        System.out.println(myStrB); //Hello one two three
        myStrB.undo();
        System.out.println(myStrB); //Hello one two
        myStrB.undo();
        System.out.println(myStrB); //Hello one
        myStrB.undo();
        System.out.println(myStrB); //Hello
    }
}
