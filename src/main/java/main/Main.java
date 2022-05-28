public class main2 {
    public static void main(String[] args) {

        System.out.println(calculateParantheses("2+4+2+(2+20*10+(7*2*6)/10*100+(4+2+(4+6+8))+2+2+1)"));
        System.out.println(calculateParantheses("(-4*(3+23)/(-9)*(-10+67-90)/(78+2)/3+4-((-9*-11)/-11)*-4)*2"));

    }

    public static double calculateParantheses(String s) {

        try{

            while (s.contains("++") || s.contains("+-") ||s.contains("-+") ||s.contains("--") ||s.contains("*+") ||s.contains("/+")){
                if (s.contains("++")) {
                    s = s.replace("++", "+");
                } else if (s.contains("+-")) {
                    s = s.replace("+-", "-");
                } else if (s.contains("-+")) {
                    s = s.replace("-+", "-");
                } else if (s.contains("--")) {
                    s = s.replace("--", "+");
                } else if (s.contains("*+")) {
                    s = s.replace("*+", "*");
                } else {
                    if (s.contains("/+")) {
                        s = s.replace("/+", "/");
                    }
                }
            }


            return Double.parseDouble(s);
        }
        catch(NumberFormatException e) {

            String result = "";
            String expression = s;
            int last = s.lastIndexOf("(");
            int first = 0;
            if(expression.contains("(")){
                for (int i = last + 1; i < expression.length(); i++) {
                    if (expression.charAt(i) == ')') {
                        first = i;
                        break;
                    }
                }
                expression = expression.substring(last + 1, first);
            }

            while (expression.contains("*") || expression.contains("/")) {
                result = multiplyAndDivide(expression);
                s = s.replace(expression, result);
                expression = result;
            }

            if(expression.contains("+") || expression.contains("-")) {
                result = addAndSubtract(expression);
            }

            String temp;
            if(s.contains("(")){
                temp = s.replace("(" + expression + ")", result);
            }else{
                temp = s.replace(expression, result);
            }


            return calculateParantheses(temp);
        }
    }


    public static String multiplyAndDivide(String s) {
        StringBuilder left;
        StringBuilder right;
        Double result;
        int openPar = s.lastIndexOf("(");
        int closePar = s.indexOf(")");
        String expression;
        if (s.contains("(")) {
            expression = s.substring(openPar + 1, closePar);
        } else {
            expression = s;
        }

        while (expression.contains("*") || expression.contains("/")) {

            left = new StringBuilder();
            right = new StringBuilder();
            boolean flag = false;
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '/') {
                    for (int j = i - 1; j >= 0; j--) {
                        if (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.') {
                            left.append(expression.charAt(j));
                        } else {
                            if(expression.charAt(j) == '-') {
                                if(j == 0) {
                                    left.append("-");
                                }
                            }
                            break;
                        }
                    }
                    left.reverse();

                    for (int j = i + 1; j < expression.length(); j++) {
                        if (Character.isDigit(expression.charAt(j)) || (expression.charAt(j) == '.') || (expression.charAt(j) == '-')) {
                            right.append(expression.charAt(j));
                        } else {
                            break;
                        }
                    }
                    if(right.charAt(right.length() - 1) == '-') {
                        right = new StringBuilder(right.substring(0, right.length() - 1));
                    }

                    if (!left.equals("")) {
                        result = Double.parseDouble(String.valueOf(left)) / Double.parseDouble(String.valueOf(right));
                        expression = expression.replace(left + "/" + right, String.valueOf(result));
                        flag = true;

                    }
                }

                if (flag) {
                    break;
                }

                if (expression.charAt(i) == '*') {
                    for (int j = i - 1; j >= 0; j--) {
                        if (Character.isDigit(expression.charAt(j)) || (expression.charAt(j) == '.')) {
                            left.append(expression.charAt(j));
                        } else {
                            if(expression.charAt(j) == '-') {
                                if(j == 0) {
                                    left.append("-");
                                }
                            }
                            break;
                        }
                    }
                    left.reverse();

                    for (int j = i + 1; j < expression.length(); j++) {
                        if (Character.isDigit(expression.charAt(j)) || (expression.charAt(j) == '.') || (expression.charAt(j) == '-')) {
                            right.append(expression.charAt(j));
                        } else {
                            break;
                        }
                    }

                    if(right.charAt(right.length() - 1) == '-') {
                        right = new StringBuilder(right.substring(0, right.length() - 1));
                    }

                    if (!left.equals("")) {
                        result = Double.parseDouble(String.valueOf(left)) * Double.parseDouble(String.valueOf(right));
                        expression = expression.replace(left + "*" + right, String.valueOf(result));
                        flag = true;

                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        s = expression;
        return s;
    }

    public static String addAndSubtract (String s) {

        char sign;
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        String expression = s;
        Double result = 0.0;
        int i = 0;

        try {
            return String.valueOf(Double.parseDouble(s));
        }catch (NumberFormatException e) {
            if(expression.charAt(0) == '-') {
                left.append('-');
                i = 1;
            }
            for (; i < expression.length(); i++) {
                if(Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.') {
                    left.append(expression.charAt(i));
                }else {

                    break;
                }
            }
            sign = expression.charAt(i++);

            for (; i < expression.length(); i++) {
                if(Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '.') || expression.charAt(i) == '-') {
                    right.append(expression.charAt(i));
                    if((right.length() > 1) && (right.charAt(right.length()-1) == '-')) {
                        right = new StringBuilder(right.substring(0, right.length()-1));
                        break;
                    }
                }else {
                    break;
                }
            }


            switch (sign) {
                case '+':
                    result = Double.parseDouble(String.valueOf(left)) + Double.parseDouble(String.valueOf(right));
                    break;
                case '-':
                    result = Double.parseDouble(String.valueOf(left)) - Double.parseDouble(String.valueOf(right));
            }

            expression = expression.replace(String.valueOf(left) + sign + right, String.valueOf(result));

            try {

                return String.valueOf(Double.parseDouble(expression));
            }catch (NumberFormatException ex) {

                return String.valueOf(addAndSubtract(expression));
            }
        }
    }
}
