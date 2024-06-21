package javacore;

public class TextBlocks {
    public static void main(String[] args) {
        textBlocks();
    }

    private static void textBlocks() {
        String str = """
                This is a text block
                example!
                """;
        System.out.println(str);
        str = """
                <html>
                    <head>
                        <title>Some title</title>
                    </head>
                    <body></body>
                </html>
                """;
        System.out.println(str);
        str = """
                Example showing \
                how to escape new \
                "line".
                """;
        System.out.println(str);
        str = """
                Example showing   <place holder>  \s
                how to escape   \s
                "spaces".
                """;
        System.out.println(str);
    }
}
