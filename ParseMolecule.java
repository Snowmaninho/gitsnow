 /* This code count the number of atoms of each element contained in the molecule
  p.s.: I know that is a terrible not refactored code, but it works
  */

import java.util.HashMap;
import java.util.Map;

class ParseMolecule {

    public static void main(String[] args) {
        System.out.println(getAtoms("K4[ON(SO3)2]2"));
    }


    public static Map<String,Integer> getAtoms(String formula) throws IllegalArgumentException {
        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLowerCase(formula.charAt(0))) {
                throw new IllegalArgumentException();
            }
            if (formula.length() > 1 && i < formula.length() - 1) {
                if (Character.isLowerCase(formula.charAt(i)) && Character.isLowerCase(formula.charAt(i + 1))) {
                    throw new IllegalArgumentException();
                }
            }
        }

        for (int d = 0; d < formula.length(); d++) {

            for (int i = 0; i < formula.length(); i++) {
                if (formula.charAt(i) == '(') { // round
                    for (int j = i + 1; j < formula.length(); j++) {
                        if (formula.charAt(j) == '(') {i = j;}
                        if (formula.charAt(j) == ')') {
                            if (j <= formula.length() - 2) {
                                if (Character.isDigit(formula.charAt(j + 1))) {
                                    int num = 0;
                                    if (j + 1 != formula.length() - 1) {
                                        if (Character.isDigit(formula.charAt(j + 2))) {
                                            num = Integer.parseInt(formula.substring(j + 1, j + 3));
                                        } else {
                                            num = Integer.parseInt(formula.substring(j + 1, j + 2));
                                        }
                                    } else {
                                        num = Integer.parseInt(formula.substring(j + 1, j + 2));
                                    }
                                    String mini = "";
                                    for (int z = 0; z < num; z++) {
                                        mini += formula.substring(i + 1, j);
                                    }
                                    String a = formula.substring(i, j + 2);
                                    formula = formula.replace(a, mini);
                                    break;
                                } else {
                                    formula = formula.replace(formula.substring(i, j + 1), formula.substring(i + 1, j));
                                    break;
                                }
                            } else {
                                formula = formula.replace(formula.substring(i, j + 1), formula.substring(i + 1, j));
                                break;
                            }
                        }
                    }

                }
                if (formula.charAt(i) == '[') {  //square
                    for (int j = i + 1; j < formula.length(); j++) {
                        if (formula.charAt(j) == '[') {i = j;}
                        if (formula.charAt(j) == ']') {
                            if (j <= formula.length() - 2) {
                                if (Character.isDigit(formula.charAt(j + 1))) {
                                    int num = 0;
                                    if (j + 1 != formula.length() - 1) {
                                        if (Character.isDigit(formula.charAt(j + 2))) {
                                            num = Integer.parseInt(formula.substring(j + 1, j + 3));
                                        } else {
                                            num = Integer.parseInt(formula.substring(j + 1, j + 2));
                                        }
                                    } else {
                                        num = Integer.parseInt(formula.substring(j + 1, j + 2));
                                    }
                                    String mini = "";
                                    for (int z = 0; z < num; z++) {
                                        mini += formula.substring(i + 1, j);
                                    }
                                    String a = formula.substring(i, j + 2);
                                    formula = formula.replace(a, mini);
                                    break;
                                } else {
                                    formula = formula.replace(formula.substring(i, j + 1), formula.substring(i + 1, j));
                                    break;
                                }
                            } else {
                                formula = formula.replace(formula.substring(i, j + 1), formula.substring(i + 1, j));
                                break;
                            }
                        }
                    }

                }
                if (formula.charAt(i) == '{') { // curly
                    for (int j = i + 1; j < formula.length(); j++) {
                        if (formula.charAt(j) == '{') {i = j;}
                        if (formula.charAt(j) == '}') {
                            if (j <= formula.length() - 2) {
                                if (Character.isDigit(formula.charAt(j + 1))) {
                                    int num = 0;
                                    if (j + 1 != formula.length()) {
                                        if (Character.isDigit(formula.charAt(j + 2))) {
                                            num = Integer.parseInt(formula.substring(j + 1, j + 3));
                                        } else {
                                            num = Integer.parseInt(formula.substring(j + 1, j + 2));
                                        }
                                    } else {
                                        num = Integer.parseInt(formula.substring(j + 1, j + 2));
                                    }
                                    String mini = "";
                                    for (int z = 0; z < num; z++) {
                                        mini += formula.substring(i + 1, j);
                                    }
                                    String a = formula.substring(i, j + 2);
                                    formula = formula.replace(a, mini);
                                    break;
                                } else {
                                    formula = formula.replace(formula.substring(i, j + 1), formula.substring(i + 1, j));
                                    break;
                                }
                            } else {
                                formula = formula.replace(formula.substring(i, j + 1), formula.substring(i + 1, j));
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < formula.length(); i++) { // all digits to atoms
            if (Character.isDigit(formula.charAt(i))) {
                if (i + 1 != formula.length()) {
                    if (Character.isDigit(formula.charAt(i + 1))) {
                        String mini = "";
                        if (Character.isLowerCase(formula.charAt(i - 1))) {
                            for (int j = 0; j < Integer.parseInt(formula.substring(i, i + 2)); j++) {
                                mini += formula.substring(i - 2, i);
                            }
                            formula = formula.replaceFirst(formula.substring(i - 2, i + 2), mini);
                        } else {
                            for (int j = 0; j < Integer.parseInt(formula.substring(i, i + 2)); j++) {
                                mini += formula.substring(i - 1, i);
                            }
                            formula = formula.replaceFirst(formula.substring(i - 1, i + 2), mini);
                        }
                    } else {
                        String mini = "";
                        if (Character.isLowerCase(formula.charAt(i - 1))) {
                            for (int j = 0; j < Character.getNumericValue(formula.charAt(i)); j++) {
                                mini += formula.substring(i - 2, i);
                            }
                            formula = formula.replaceFirst(formula.substring(i - 2, i + 1), mini);
                        } else {
                            for (int j = 0; j < Character.getNumericValue(formula.charAt(i)); j++) {
                                mini += formula.substring(i - 1, i);
                            }
                            formula = formula.replaceFirst(formula.substring(i - 1, i + 1), mini);
                        }
                    }
                } else {
                    String mini = "";
                    if (Character.isLowerCase(formula.charAt(i - 1))) {
                        for (int j = 0; j < Character.getNumericValue(formula.charAt(i)); j++) {
                            mini += formula.substring(i - 2, i);
                        }
                        formula = formula.replaceFirst(formula.substring(i - 2, i + 1), mini);
                    } else {
                        for (int j = 0; j < Character.getNumericValue(formula.charAt(i)); j++) {
                            mini += formula.substring(i - 1, i);
                        }
                        formula = formula.replaceFirst(formula.substring(i - 1, i + 1), mini);
                    }
                }
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = formula.length() - 1; i >= 0; i--) {
            if (Character.isLowerCase(formula.charAt(i))) {
                if (map.containsKey(formula.substring(i - 1, i + 1))) {
                    map.put(formula.substring(i - 1, i + 1), map.get(formula.substring(i - 1, i + 1)) + 1);
                } else {
                    map.put(formula.substring(i - 1, i + 1), 1);
                }
                i--;
            } else {
                if (map.containsKey(formula.substring(i, i + 1))) {
                    map.put(formula.substring(i, i + 1), map.get(formula.substring(i, i + 1)) + 1);
                } else {
                    map.put(formula.substring(i, i + 1), 1);
                }
            }
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (pair.getKey().matches("[{(\\[)}\\]0-9a-z]")) {
                throw new IllegalArgumentException();
            }
        }
        return map;
    }
}