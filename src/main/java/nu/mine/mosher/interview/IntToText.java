/*
    Copyright © 2017, Christopher Alan Mosher, Shelton, CT, USA.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package nu.mine.mosher.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntToText {
    public static void main(final String... args) {
        /* extracted from problem definition: */
        int number = 7297;
        String strNumber = convertToText(number);
        // print number and text
        System.out.println("Integer: " + number + "\nText: " + strNumber);
    }



    /* Constants: English language words for numbers */
    /* @formatter:off */
    private static final String[] under20 = {
        "", "One", "Two", "Three", "Four",
        "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
        "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    /* We're only handling int data-type, so we don't need higher than billions. */
    private static final String[] thousands = {
        "", "Thousand", "Million", "Billion"
    };
    /* @formatter:on */

    /* space between words: */
    private static final String S = " ";



    /**
     * Converts the given input int into a corresponding string of English words.
     * @param input int to be converted
     * @return string of words
     */
    public static String convertToText(final int input) {
        return convertIntToText(input);
    }



    private static String convertIntToText(final long n) {
        assert Integer.MIN_VALUE <= n && n <= Integer.MAX_VALUE;

        final String s;

        /* Three cases: positive, negative, or zero */
        if (n > 0) {
            s = convertPositiveIntToText(n);
        } else if (n < 0) {
            /* This would overflow an int for MIN_VALUE, so we use a long: */
            s = "Negative" + S + convertPositiveIntToText(-n);
        } else {
            s = "Zero";
        }

        return s;
    }

    private static String convertPositiveIntToText(final long n) {
        assert 0 < n && n <= ((long)Integer.MAX_VALUE)+1;

        /*
            Build list of strings in thousands' groups, least to greatest,
            then reverse the list and join it into a string.
         */
        final List<String> rsThousands = new ArrayList<>();
        convertThousandsFromLeastToGreatest(n, rsThousands);
        Collections.reverse(rsThousands);
        return joinNicelyWithSpaces(rsThousands);
    }

    private static void convertThousandsFromLeastToGreatest(final long input, final List<String> r) {
        int iThou = 0; /* which thousand's group we're in now (0 to 3) */

        for (long n = input; n > 0; n /= 1000, ++iThou) {
            r.add(convertThousand(iThou, (int) (n % 1000)));
        }
    }

    private static String convertThousand(final int iThou, final int n) {
        assert 0 <= iThou && iThou < thousands.length;
        assert 0 <= n && n < 1000;

        String ret = "";



        /* the hundreds part */
        final int iHund = n / 100;
        if (iHund > 0) {
            ret += S + under20[iHund];
            ret += S + "Hundred";
        }

        final int h = n % 100;

        /* the tens part */
        ret += S + tens[h / 10];

        /* the ones part */
        /* we can't use just h % 10, due to special case teens */
        ret += S + under20[h < 20 ? h : h % 10];



        /* the thousand's group word */
        if (n > 0) {
            ret += S + thousands[iThou];
        }

        return ret;
    }

    /**
     * Joins list, with spaces, then converts any double spaces to single spaces,
     * and removes any leading and trailing spaces.
     *
     * @param rs array to join
     * @return array joined as a string
     */
    private static String joinNicelyWithSpaces(final List<String> rs) {
        return String.join(S, rs).replaceAll(S + "+", S).trim();
    }
}
