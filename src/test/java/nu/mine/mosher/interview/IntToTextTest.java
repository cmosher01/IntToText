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

import org.junit.jupiter.api.Test;

import static nu.mine.mosher.interview.IntToText.convertToText;
import static org.junit.jupiter.api.Assertions.*;

class IntToTextTest {
    void t(final String expected, final int input) {
        assertEquals(expected, convertToText(input));
    }

    @Test
    void t7297() {
        t("Seven Thousand Two Hundred Ninety Seven", 7297);
    }

    @Test
    void t0() {
        t("Zero", 0);
    }

    @Test
    void t1() {
        t("One", 1);
    }

    @Test
    void t15() {
        t("Fifteen", 15);
    }

    @Test
    void t30() {
        t("Thirty", 30);
    }

    @Test
    void t78() {
        t("Seventy Eight", 78);
    }

    @Test
    void t346() {
        t("Three Hundred Forty Six", 346);
    }

    @Test
    void t500() {
        t("Five Hundred", 500);
    }

    @Test
    void t30000() {
        t("Thirty Thousand", 30000);
    }

    @Test
    void t1000000000() {
        t("One Billion", 1000000000);
    }

    @Test
    void t1000000001() {
        t("One Billion One", 1000000001);
    }

    @Test
    void t1234567() {
        t("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", 1234567);
    }

    @Test
    void t4100000() {
        t("Four Million One Hundred Thousand", 4100000);
    }

    @Test
    void t2147483647() {
        t("Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven", Integer.MAX_VALUE);
    }

    @Test
    void tneg1() {
        t("Negative One", -1);
    }

    @Test
    void tneg2147483648() {
        t("Negative Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Eight", Integer.MIN_VALUE);
    }
}
