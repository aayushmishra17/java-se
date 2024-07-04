package javacore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegularExpression
 */
public class RegularExpression {

    public static void main(String[] args) {
        matcherMethods();
        regularExpression();
    }

    private static void matcherMethods() {
        // Source : https://jenkov.com/tutorials/java-regex/matcher.html
        Pattern pattern = Pattern.compile("ab");
        Matcher matcher = pattern.matcher("ab");

        // matches()
        System.out.println("matches() method returns \"true\" only if the pattern matches the entire string - "
                + matcher.matches());
        matcher = pattern.matcher("abc");
        System.out.println("matches() method returns \"false\" if the pattern doesn't matche the entire string - "
                + matcher.matches());

        // lookingAt()
        /*
         * The lookingAt() method only matches the regular expression against the
         * beginning of the text, whereas matches() matches the regular expression
         * against the whole text.
         */
        String text = "This is the text to be searched for occurrences of the http:// pattern.";

        String patternString = "This is the";

        pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(text);

        System.out.println("lookingAt = " + matcher.lookingAt());
        System.out.println("matches   = " + matcher.matches());

        // find() start() end()
        /*
         * find() - If multiple matches can be found in the text, the find() method will
         * find the first, and then for each subsequent call to find() it will move to
         * the next match.
         * start() and end() will give the indexes into the text where the found match
         * starts and ends. Actually end() returns the index of the character just after
         * the end of the matching section.
         */
        text = "This is the text which is to be searched for occurrences of the word 'is'.";
        patternString = "is";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("found: " + count + " : "
                    + matcher.start() + " - " + matcher.end());
        }

        // reset()
        /*
         * The Matcher reset() method resets the matching state internally in the
         * Matcher. In case you have started matching occurrences in a string via the
         * find() method, the Matcher will internally keep a state about how far it has
         * searched through the input text. By calling reset() the matching will start
         * from the beginning of the text again.
         */

        // group(int groupNo)
        /*
         * The group with number 0 is always the whole regular expression. To get access
         * to a group marked by parentheses you should start with group numbers 1.
         */
        text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        patternString = "(John) (.+?) ";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("found: " + matcher.group(1) +
                    " " + matcher.group(2));
        }

        // groups inside groups
        /*
         * When groups are nested inside each other, they are numbered based on when the
         * left paranthesis of the group is met. Thus, group 1 is the big group. Group 2
         * is the group with the expression John inside. Group 3 is the group with the
         * expression .+? inside.
         */

        // replaceAll() and replaceFirst()
        /*
         * The replaceAll() method replaces all matches of the regular expression. The
         * replaceFirst() only replaces the first match.
         */
        text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        patternString = "((John) (.+?)) ";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(text);

        String replaceAll = matcher.replaceAll("Joe Blocks ");
        System.out.println("replaceAll   = " + replaceAll);

        String replaceFirst = matcher.replaceFirst("Joe Blocks ");
        System.out.println("replaceFirst = " + replaceFirst);

        // appendReplacement() + appendTail()
        /*
         * When you have found a match using the find() method, you can call the
         * appendReplacement(). Doing so results in the characters from the input text
         * being appended to the StringBuffer, and the matched text being replaced. Only
         * the characters starting from then end of the last match, and until just
         * before the matched characters are copied.
         * Once the last match has been found, a part of the input text will still not
         * have been copied into the StringBuffer. This is the characters from the end
         * of the last match and until the end of the input text. By calling
         * appendTail() you can append these last characters to the StringBuffer too.
         */
        text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        patternString = "((John) (.+?)) ";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "Joe Blocks ");
            System.out.println(stringBuffer.toString());
        }
        matcher.appendTail(stringBuffer);

        System.out.println(stringBuffer.toString());
    }

    private static void regularExpression() {
        // java.util.regex
        Pattern p = Pattern.compile("foo");
        Matcher m = p.matcher("foo");
        /*
         * The find method keeps advancing through the input text and returns true for
         * every match, so we can use it to find the match count as well.
         */
        System.out.println("foo == foo? " + m.find());

        // Practice site- https://regex101.com/

        /*
         * . wildcard, matches any single character.
         * [] - OR
         * ^ - NOR
         * [[]] - union
         * && - intersection
         * [x-y] - range
         * | - alternate match, a|b - match either a or b.
         */
        int matches = runTest("foo.", "foofoo"); // it would match foof
        matches = runTest("[abc]", "cba"); // match count = 3
        matches = runTest("[bcr]at", "bat cat rat"); // match count = 3
        matches = runTest("[^bcr]at", "sat mat eat"); // match count = 3
        matches = runTest("[a-zA-Z]", "abcd"); // match count = 4
        matches = runTest("[1-5]", "some text with number 125"); // match count = 3
        matches = runTest("3[0-5]", "text with number 1 2 341"); // match count = 1, matches "34"
        matches = runTest("[1-3[7-9]]", "123456789"); // it would match 123789; match count = 6
        matches = runTest("[1-6&&[3-9]]", "123456789"); // it would match 3456; match count = 4
        matches = runTest("[0-9&&[^2468]]", "123456789"); // it would match 13579; match count = 5
        matches = runTest("a|b", "a or b, pick one!"); // match count = 2

        /*
         * Predefined character classes-
         * \d - [0-9] matches digits
         * \D - [^0-9] matches non digits
         * \s - matches white spaces
         * \S - matches non white spaces
         * \w - matches word characters [a-zA-Z_0-9]
         * \W - matches non word characters
         */
        matches = runTest("\\d", "123");// match count = 3
        matches = runTest("\\D", "a6c");// match count = 2
        matches = runTest("\\s", "a c"); // match count = 1
        matches = runTest("\\S", "a c"); // match count = 2
        matches = runTest("\\w", "hi!"); // match count = 2
        matches = runTest("\\W", "hi!"); // match count = 1

        /*
         * Quantifiers-
         * ? - match a character zero or one time, same as ch{0, 1}
         * * - match a character zero or limitless times, same as ch{0,}
         * + - matches a character one or limitless times, same as ch{1,}
         * ch{x,y} - when we use a range in the brace, the match will be greedy,
         * matching from the higher end of the range.
         * ch{x,y}? - the API allows us to specify a lazy or reluctant approach such
         * that the matcher can start from the lower end of the range.
         * .* - greedy quantifier
         * .*? - lazy quantifier
         * The default regex behavior is greedy - match till the end and then backtrack.
         * To make it super greedy - match without backtrack, add a + at the end, e.g.:
         * A.++a
         * To make it lazy - match only as little as possible, add a ? at the end, e.g.:
         * A.+?a
         * Default is greedy mode - match with backtrack, e.g.: A.+a
         */
        matches = runTest("a?", "hi"); // match count = 3 -> zero length match also counts.
        matches = runTest("a{0,1}", "hi"); // match count = 3 -> zero length match also counts.
        matches = runTest("a*", "hi"); // match count = 3 -> zero length match also counts.
        matches = runTest("a{0,}", "hi"); // match count = 3 -> zero length match also counts.
        matches = runTest("a+", "hi"); // match count = 0
        matches = runTest("a{1,}", "hi"); // match count = 0
        matches = runTest("a{3}", "aaaaaa"); // match count = 2 -> aaa aaa
        matches = runTest("a{3}", "aa"); // match count = 0
        matches = runTest("a{2,3}", "aaaa"); // match count = 1
        matches = runTest("a{2,3}?", "aaaa"); // match count = 2
        matches = runTest("a.*a", "greedy can be dangerous at times"); // match count = 1, matches -
                                                                       // "an be dangerous a"
        matches = runTest("r\\w*?", "r re regex");// match count = 3, matches - "r" 3 times

        /*
         * Capturing groups-
         * The API also allows us to treat multiple characters as a single unit through
         * capturing groups. It will attach numbers to the capturing groups, and allow
         * back referencing using these numbers.
         * \# or \g# or \g{#} where # is a digit.
         * 
         */
        matches = runTest("(\\d\\d)", "12"); // match count = 1 -> matches only when an input text contains two digits
                                             // next to each other
        matches = runTest("(\\d\\d)", "1212"); // match count = 2
        matches = runTest("(.)\\1", "Repeat letters"); // match count = 1, matches - "tt"
        matches = runTest("(\\d\\d)\\1", "1212"); // match count = 1 -> \\1 back references the first group pattern.
        // the above pattern is same as -> (\\d\\d)(\\d\\d) here without back
        // referencing we need to repeast the same pattern.

        matches = runTest("(\\d\\d)\\1\\1\\1", "12121212"); // match count = 1
        matches = runTest("(\\d\\d)\\1", "1213"); // match count = 0 -> if we change even one character the match will
                                                  // fail.

        /*
         * Boundary matchers-
         * ^ - begining of string
         * $ - end of string
         * \b - match only when the required text is found at a word boundary, space is
         * a word boundary. Basically, it matches the last character between a word and
         * a non-word character. It would also match the start and end of a string.
         * \B - non word boundary
         */
        matches = runTest("^dog", "dogs are friendly"); // match count = 1
        matches = runTest("dog$", "Man's best friend is a dog"); // match count = 1
        matches = runTest("\\b", "word boundaries are odd"); // match count = 5
        matches = runTest("d\\b", "word boundaries are odd"); // match count = 2
        matches = runTest("\\bdog\\b", "a dog is friendly"); // match count = 1
        matches = runTest("\\bdog\\b", "dog is man's best friend"); // match count = 1 -> empty string at the beginning
                                                                    // of a line is also a word boundary
        matches = runTest("\\bdog\\b", "snoop dogg is a rapper"); // match count = 0
        matches = runTest("\\bdog\\B", "snoop dogg is a rapper"); // match count = 1

        /*
         * Pattern class methods also take flag arguements.
         * Pattern.CASE_INSENSITIVE - case insensitive match, same as (?i)
         * Pattern.COMMENTS - The Java API allows us to include comments using # in the
         * regex, same as (?x)
         */
        matches = runTest("dog", "This is a Dog", Pattern.CASE_INSENSITIVE); // match count = 1
        matches = runTest("(?i)dog", "This is a Dog"); // match count = 1
        matches = runTest("dog$  #check end of text", "This is a dog", Pattern.COMMENTS); // match count = 1
        matches = runTest("(?x)dog$  #check end of text", "This is a dog"); // match count = 1
    }

    private static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    private static int runTest(String regex, String text, int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);

        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}