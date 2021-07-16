import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Parser {
    private int currentTokenKey;
    private LinkedHashMap<Integer, String> tokens;
    private ButtonGroup buttonGroup;
    private JRadioButton radioButton;
    private JTextField textField;
    private JFrame frame;
    private JPanel panel = new JPanel();
    private boolean hasFrame;
    private String widgetString;

    public Parser(LinkedHashMap<Integer, String> tokens) {
        currentTokenKey = 1;
    
        this.tokens = tokens;
    }
    // parseGUI() by the specified grammar.
    protected void parseGUI() {
        System.out.println("Test (3/3): Parse GUI");
        int width, height; // frame width and height

        System.out.println("\tTest (1/7): Create JFrame");
        if (currentToken().equals("Window")) { // Check if first token is "Window"
            hasFrame = true;
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            System.out.println("\t\tSucess: Created JFrame");
            getNextToken(); // second token is the frame title
            System.out.println("\tTest (2/7): Set JFrame Title " + currentToken());

            if (currentToken().equals("\"")) {
                getNextToken();
                frame.setTitle(currentToken());
                System.out.println("\t\tSucess: Set JFrame Title - " + currentToken());
                getNextToken();

                if (currentToken().equals("\"")) {
                    getNextToken();

                    // JFrame Parameters
                    if (currentToken().equals("(")) {

                        getNextToken();
                        System.out.println("\tTest (3/7): Add Width to JFrame");
                        width = toInt(currentToken());
                        System.out.println("\t\tSucess: Added Width to JFrame " + width);
                        getNextToken();

                        if (currentToken().equals(",")) {
                            getNextToken();
                            System.out.println("\tTest (4/7): Add Height to JFrame");
                            height = toInt(currentToken());
                            System.out.println("\t\tSucess: Added Height " + height);
                            getNextToken();

                            if (currentToken().equals(")")) {
                                System.out.println("\tTest (5/7): Set JFrame Size");
                                frame.setSize(width, height); // set frame size
                                System.out.println("\t\tSucess: JFrame Size Set");
                                getNextToken();
                                System.out.println("\tTest (6/7): Parse JFrame Layout");
                                if (parseLayout()) {
                                    System.out.println("\t\tSucess: Parsed and Set JFrame Layout.");
                                    System.out.println("\tTest (7/7): Parse JFrame Widgets");
                                    if (parseWidgets()) {
                                        System.out.println("\t\tSucess: Parsed and Added JFrame Widgets.");
                                        if (currentToken().equals("End")) {
                                            getNextToken();
                                            if (currentToken().equals(".")) {
                                                System.out.println("\n\tSucess: GUI Parsing Done. Showing Window...");
                                                frame.setLocationRelativeTo(null);
                                                frame.setVisible(true);
                                                // Complete
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\n\tFailed: Cannot Parse GUI. Token " + currentTokenKey + ": " + currentToken()
                + " Incorrect Syntax. View Log.");
        System.exit(0);
    }

    // parseLayout()
    private boolean parseLayout() {
        System.out.println("\t\tTest (1/1): Parse Layout");
        if (currentToken().equals("Layout")) {
            getNextToken();
            if (parseLayoutType()) {
                if (currentToken().equals(":")) {
                    getNextToken();
                    System.out.println("\t\t\tSuccess: Parsed Layout");
                    return true;
                }
            }
        }
        return false;
    }

    // parseLayoutType()

    private boolean parseLayoutType() {
        int rows, columns, columnSpacing, rowSpacing;
        System.out.println("\t\t\tTest (1/1): Parse LayoutType");
        if (currentToken().equals("Flow")) {
            System.out.println("\t\t\t\tTest (1/1): Parse and Set FlowLayout");
            if (hasFrame) {
                frame.setLayout(new FlowLayout());
            } else {
                panel.setLayout(new FlowLayout());
            }
            getNextToken();
            System.out.println("\t\t\t\t\tSuccess: Parsed and Set FlowLayout");
            System.out.println("\t\t\t\tSucess: Parse LayoutType");
            return true;
        } else if (currentToken().equals("Grid")) {
            System.out.println("\t\tTest (2/2): Parse GridLayout");
            getNextToken();

            // Grid Layout Parameters
            System.out.println("\t\t\tTest (1/2): Parse and Set GridLayout(ROW, COLUMN)");
            if (currentToken().equals("(")) { // Grid (NUMBER, NUMBER)
                getNextToken();
                System.out.println("\t\tTest (1/2): Parse GridLayout Rows");
                rows = toInt(currentToken());
                System.out.println("\t\t\tSucess: Parsed GridLayout Rows");
                getNextToken();
                if (currentToken().equals(",")) {
                    getNextToken();

                    System.out.println("\t\tTest (2/2): Parse GridLayout Columns");

                    columns = toInt(currentToken()); // get columns

                    System.out.println("\t\t\tSuccess: Parsed GridLayout Columns");
                    getNextToken();

                    // if no more parameter values i.e no row/column spacing
                    if (currentToken().equals(")")) {
                        // Set Layout and check for main panel (nested panels do not have frames)
                        if (hasFrame)
                            frame.setLayout(new GridLayout(rows, columns));
                        else
                            panel.setLayout(new GridLayout(rows, columns));
                        System.out.println("\t\t\t\tSuccess: Parsed and Set GridLayout(ROW,COLUMN)");
                        getNextToken();
                        System.out.println("\t\t\t\tSucess: Parse LayoutType");
                        return true;
                    } else if (currentToken().equals(",")) {
                        System.out.println(
                                "\t\t\tTest (1/1): Parse and Set GridLayout(ROW, COLUMN, ROW_SPACING, COLUMN_SPACING)");
                        getNextToken();

                        System.out.println("\t\t\t\tTest (1/2) Parse GridLayout Column Spacing");
                        columnSpacing = toInt(currentToken());

                        System.out.println("\t\t\t\t\tSuccess: Parsed GridLayout Column Spacing");
                        getNextToken();
                        if (currentToken().equals(",")) {
                            getNextToken();

                            System.out.println("\t\t\t\tTest (2/2) Parse GridLayout Column Spacing");

                            rowSpacing = toInt(currentToken());
                            System.out.println("\t\t\t\t\tSuccess: Parsed GridLayout Row Spacing");
                            getNextToken();
                            if (currentToken().equals(")")) {
                                if (hasFrame)
                                    frame.setLayout(new GridLayout(rows, columns, columnSpacing, rowSpacing));
                                else
                                    panel.setLayout(new GridLayout(rows, columns, columnSpacing, rowSpacing));
                                getNextToken();
                                System.out.println(
                                        "\t\t\t\tSuccess: Parsed GridLayout(ROWS,COLUMNS,COLUMN_SPACING,ROW_SPACING)");
                                System.out.println("\t\t\t\tSucess: Parse LayoutType");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\t\t\t\tFailed: Cannot Parse LayoutType");
        return false;
    }

    // toInt
    public int toInt(String token) {
        int number;
        try {
            number = Integer.parseInt(token);
            return number;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Failed: You have enter an invalid NUMBER. \"" + currentToken() + "\" ",
                    "Invalid NUMBER Error", JOptionPane.PLAIN_MESSAGE);
        }
        return 0;
    }

    // parseWidgets()
    private boolean parseWidgets() {
        if (parseWidget()) {
            if (parseWidgets())
                return true;
            return true;
        }
        return false;
    }

    // parseWidget()
    private boolean parseWidget() {
        System.out.println("\t\tTest (1/1): Parse Widget: " + currentToken());
        switch (currentToken()) {
            case "Button":
                getNextToken();

                if (currentToken().equals("\"")) {
                    getNextToken();
                    widgetString = currentToken();
                    getNextToken();
                    if (currentToken().equals("\"")) {
                        getNextToken();
                        if (currentToken().equals(";")) {
                            JButton button = new JButton(widgetString);
                            addComponent(button, true);
                            getNextToken();
                            System.out.println("\t\t\tSuccess: Parsed Button with STRING - " + widgetString);
                            return true;
                        }
                    }
                }
                break;
            case "Panel":
                addComponent(panel);
                hasFrame = false;
                getNextToken();

                System.out.println("\t\t\tTest (1/2): Parse Panel Layout");
                if (parseLayout()) {
                    System.out.println("\t\t\t\tSucess: Parsed Panel Layout");
                    System.out.println("\t\t\tTest (2/2): Parse Panel Widgets");
                    if (parseWidgets()) {
                        System.out.println("\t\t\t\tSucess: Parsed Panel Widgets");

                        if (currentToken().equals("End")) {
                            getNextToken();
                            if (currentToken().equals(";")) {
                                getNextToken();
                                System.out.println("\t\t\tSuccess: Parsed Panel");
                                return true;
                            }
                        }
                    }
                }
                break;
            case "Group":
                buttonGroup = new ButtonGroup();
                getNextToken();
                if (parseRadioButtons()) {
                    if (currentToken().equals("End")) {
                        getNextToken();
                        if (currentToken().equals(";")) {
                            getNextToken();
                            System.out.println("\t\t\tSuccess: Parsed Group");
                            return true;
                        }
                    }
                }
                break;
            case "Textfield":
                int size;
                getNextToken();
                System.out.println("\t\t\t\tTest (1/1): Parse Textfield Size");
                size = toInt(currentToken());
                System.out.println("\t\t\t\t\tSuccess: Parsed Textfield Size");
                getNextToken();

                if (currentToken().equals(";")) {
                    addComponent(textField = new JTextField(size));

                    getNextToken();
                    System.out.println("\t\t\tSuccess: Parsed TextField");
                    return true;
                }
                break;
            case "Label":
                getNextToken();

                if (currentToken().equals("\"")) {
                    getNextToken();
                    widgetString = currentToken();
                    getNextToken();
                    if (currentToken().equals("\"")) {
                        getNextToken();
                        if (currentToken().equals(";")) {
                            addComponent(new JLabel(widgetString));
                            getNextToken();
                            System.out.println("\t\t\tSuccess: Parsed Label " + widgetString);
                            return true;
                        }
                    }
                }
                break;
            default:
                return false;
        } // end switch
        return false;
    }

    // currentToken() returns the value of in the LinkedHashMap based on the
    // currentTokenKey

    private String currentToken() {
        String token = tokens.get(currentTokenKey);

        if (token == null) {
            System.out.println("Invalid Token in Syntax. Does the file end with 'End.'?");
            System.exit(0);
        }

        return token;
    }

    // parseRadioButtons()
    private boolean parseRadioButtons() {
        if (parseRadioButton()) {
            if (parseRadioButtons())
                return true;
            return true;
        }
        return false;
    }

    // parseRadioButton()
    private boolean parseRadioButton() {
        if (currentToken().equals("Radio")) {
            System.out.println("\t\t\t\tTest (1/1): Parse Radio Button");
            getNextToken();
            if (currentToken().equals("\"")) {
                getNextToken();
                widgetString = currentToken();
                getNextToken();
                if (currentToken().equals("\"")) {
                    getNextToken();
                    if (currentToken().equals(";")) {
                        radioButton = new JRadioButton(widgetString);
                        buttonGroup.add(radioButton);
                        addComponent(radioButton);
                        getNextToken();
                        System.out.println("\t\t\t\t\tSucess: Parsed Radio Button");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // add Component to Frame or Panel
    public void addComponent(Component component) {
        if (hasFrame)
            frame.add(component);
        else
            panel.add(component);
    }

    // Overload addComponent() to handle nested panels
    public void addComponent(JPanel panel) {
        if (hasFrame)
            frame.add(panel);
        else
            panel.add(panel = new JPanel());
    }

    // Overload addComponent() for Buttons i.e action listeners
    public void addComponent(JButton button, Boolean isButton) {
        if (isButton) {
            if (hasFrame) {
                button.addActionListener((ActionEvent e) -> {
                    textField.setText(button.getText());
                });
                frame.add(button);
            } else {
                button.addActionListener((ActionEvent e) -> {
                    button.setText(button.getText());
                });
                panel.add(button);
            }
        }
    }

    // get the next token via index (readability purposes)
    public void getNextToken() {
        currentTokenKey++;
    }
}
