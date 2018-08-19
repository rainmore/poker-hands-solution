package au.com.rainmore;

import au.com.rainmore.cards.Card;
import au.com.rainmore.cards.Suit;
import au.com.rainmore.cards.Value;
import au.com.rainmore.hands.Hand;
import au.com.rainmore.hands.HandComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static HandComparator comparator = new HandComparator();

    private static int result1 = 0;
    private static int result2 = 0;

    public static void main(String[] args) {

        testStdInput();

        System.out.println(String.format("Player 1: %d hands", result1));
        System.out.println(String.format("Player 2: %d hands", result2));
    }

    private static void testStdInput() {
        Scanner scanner = new Scanner(System.in);

        List<String> stringList = new ArrayList<>();
        String line;

        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            stringList.add(line);
        }

        scanner.close();

        if (!stringList.isEmpty()) {
            stringList.stream()
                    .map(str -> str.split(" "))
                    .forEach(strArray -> compare(strArray));
        }

    }

    private static void test1() {
        String[] a = {"4H", "4C", "6S", "TS", "KD", "2C", "3S", "9S", "9D", "JD"};
        String[] a1 = {"5D", "8C", "9S", "JS", "KD", "2C", "5C", "7D", "8S", "QH"};
        String[] a2 = {"2D", "9C", "AS", "AH", "AC", "3D", "6D", "7D", "JD", "QD"};
        String[] a3 = {"4D", "6S", "9H", "QH", "QC", "3D", "6D", "7H", "QD", "QS"};
        String[] a4 = {"2H", "2D", "4C", "4D", "4S", "3C", "3D", "3S", "9S", "9D"};

        compare(a);
        compare(a1);
        compare(a2);
        compare(a3);
        compare(a4);
    }

    private static Card buildCard(String str) {
        Value value = Value.forValue(str.substring(0, str.length() - 1));
        Suit suit = Suit.forValue(str.substring(str.length() - 1));
        return new Card(value, suit);
    }

    private static int compare(String[] str) {
        List<Card> cards = Arrays.stream(str).map(Main::buildCard).collect(Collectors.toList());
        Hand hand1 = new Hand(cards.subList(0, 5).toArray(new Card[5]));
        Hand hand2 = new Hand(cards.subList(5, 10).toArray(new Card[5]));

        int result = comparator.compare(hand1, hand2);

        if (result > 0) result1++;
        if (result < 0) result2++;

        return result;
    }
}
