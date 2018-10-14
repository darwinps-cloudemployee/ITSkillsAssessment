package com.assessment.it.itskillsassessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class ExamActivity extends AppCompatActivity {

    private ArrayList<Question> questions = new ArrayList<>();
    int index = 0;
    int correctAnswers = 0;
    Question currentq = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String option = intent.getStringExtra("Option");


         Button submitButton = (Button) findViewById(R.id.button_submit);
         final TextView questionText = (TextView) findViewById(R.id.textView_questions);
         final RadioButton optionA =(RadioButton) findViewById(R.id.radioButton_optionA);
         final RadioButton optionB =(RadioButton) findViewById(R.id.radioButton_optionB);
         final RadioButton optionC =(RadioButton) findViewById(R.id.radioButton_optionC);
         final RadioButton optionD =(RadioButton) findViewById(R.id.radioButton_optionD);


         optionA.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionB.setChecked(false);
                optionC.setChecked(false);
                optionD.setChecked(false);
            }
        });

        optionB.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionA.setChecked(false);
                optionC.setChecked(false);
                optionD.setChecked(false);
            }
        });

        optionC.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionA.setChecked(false);
                optionB.setChecked(false);
                optionD.setChecked(false);
            }
        });

        optionD.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionA.setChecked(false);
                optionB.setChecked(false);
                optionC.setChecked(false);
            }
        });

         questions = createExamQuestions(option);
         final ListIterator<Question> questionIterator = questions.listIterator();
         currentq = questions.get(index);
         loadQuestion(questionText, optionA, optionB, optionC, optionD, currentq);
         questionIterator.next();
         submitButton.setOnClickListener(new Button.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if(!hasSelected(optionA,optionB,optionC,optionD))
                 {
                     Snackbar.make(v, "Please select your answer", Snackbar.LENGTH_LONG)
                             .setAction("Action", null).show();
                 }
                 else
                 {
                     if(questionIterator.hasNext())
                     {

                         index = questionIterator.nextIndex();
                         computeResult(optionA,optionB,optionC,optionD,currentq);
                         currentq = questions.get(index);
                         loadQuestion(questionText, optionA, optionB, optionC, optionD, currentq);
                         questionIterator.next();
                     }
                     else
                     {
                         computeResult(optionA,optionB,optionC,optionD,currentq);
                         Float result = (float) correctAnswers / questions.size();
                         Intent intent = new Intent(ExamActivity.this,ResultActivity.class);
                         intent.putExtra("result",result);
                         startActivity(intent);

                     }
                 }

             }
         });


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private boolean hasSelected(RadioButton optionA, RadioButton optionB,
                                RadioButton optionC, RadioButton optionD) {
        boolean _hasSelected = false;

        if(optionA.isChecked() || optionB.isChecked() || optionC.isChecked() || optionD.isChecked())
        {
            _hasSelected = true;
        }
        else
        {
            _hasSelected = false;
        }

        return _hasSelected;
    }

    private void computeResult(RadioButton optionA, RadioButton optionB,
                               RadioButton optionC, RadioButton optionD, Question q) {
        String c = q.getCorrectAnswer();

        if(optionA.isChecked())
        {
            if(c.equals("A"))
                correctAnswers++;
        }

        if(optionB.isChecked())
        {
            if(c.equals("B"))
                correctAnswers++;
        }

        if(optionC.isChecked())
        {
            if(c.equals("C"))
                correctAnswers++;
        }

        if(optionD.isChecked())
        {
            if(c.equals("D"))
                correctAnswers++;
        }

    }

    private void loadQuestion(TextView questionText, RadioButton optionA, RadioButton optionB,
                              RadioButton optionC, RadioButton optionD, Question q) {
        questionText.setText(q.getQuestion());

        String moptionA =  q.getOptionA();
        String moptionB = q.getOptionB();
        String moptionC = q.getOptionC();
        String moptionD = q.getOptionD();

        optionA.setVisibility(View.INVISIBLE);
        optionB.setVisibility(View.INVISIBLE);
        optionC.setVisibility(View.INVISIBLE);
        optionD.setVisibility(View.INVISIBLE);

        optionA.setChecked(false);
        optionB.setChecked(false);
        optionC.setChecked(false);
        optionD.setChecked(false);

        if(!moptionA.equals(""))
        {
            optionA.setVisibility(View.VISIBLE);
            optionA.setText(q.getOptionA());
        }

        if(!moptionB.equals(""))
        {
            optionB.setVisibility(View.VISIBLE);
            optionB.setText(q.getOptionB());
        }

        if(!moptionC.equals(""))
        {
            optionC.setVisibility(View.VISIBLE);
            optionC.setText(q.getOptionC());
        }

        if(!moptionD.equals(""))
        {
            optionD.setVisibility(View.VISIBLE);
            optionD.setText(q.getOptionD());
        }

    }


    private ArrayList<Question> createExamQuestions(String option)
    {
        ArrayList<Question> QuestionList = new ArrayList<Question>();

        if(option.equals("Easy"))
        {
            QuestionList = getEasyQuestion();
        }
        if(option.equals("Intermediate"))
        {
            QuestionList = getIntermediateQuestion();
        }
        if(option.equals("Advanced"))
        {
            QuestionList = getAdvancedQuestion();
        }

        return QuestionList;









    }




    private ArrayList<Question> getEasyQuestion() {
        Question question1 = new Question("How do you open a program such as a Microsoft Word when there are no icons on the desktop?", "Right click to reveal all icons.", "Restart the computer.", "It is not possible to open program if no icons are on the desktop.", "Cick the start button and select program from the menu.", "C");
        Question question2 = new Question("How do you minimize or maximize a program in Windows?", "Right click on the mouse.", "Go to \"File\" and select minimize or maximize.", "Top right corner, dash or square.", "This function can not be done.", "C");
        Question question3 = new Question("How do you close a window on your desktop?","Go to \"File\" and select \"Close\".", "Press the \"Ctrl\" key on the keyboard.", "Use the mouse to press the button with the \"X\" in it at the top right corner of the screen.", "Use the mouse to press the button with the _ in it at the top right corner of the screen.", "C");
        Question question4 = new Question("A word processing file can be attached to an e-mail message?", "Yes", "No", "Depends on what type of file.", "Only to select email addresses.", "A");
        Question question5 = new Question("What does the \"Reply to All\" e-mail function do?", "It will generate a reply message to all of the recipients of the message.", "It will send a reply message to every entry in your address book.", "It will generate a reply message only to the sender of the message.", "This function is not possible.", "A");
        Question question6 = new Question("\"Desktop\" is a computer term that refers to?", "Something that is for the computer programmer only.", "The part of your work area where the computer monitor sits.", "The initial screen showing icons for folders, applications and files.", "The desk that your computer is stationed on.", "D");
        Question question7 = new Question("Who is father of modern computers?", "Abraham Lincoln", "James Gosling", "Charles Babbage", "Gordon E. Moore", "C");
        Question question8 = new Question("How many generations of computers we have?", "4", "5", "6", "7", "B");
        Question question9 = new Question("________ controls the way in which the computer system functions and provides a means by which users can interact with the computer.", "The operating system", "The motherboard", "The platform", "Application software", "A");
        Question question10 = new Question("The difference between people with access to computers and the Internet and those without this access is known as the:", "digital divide.", "Internet divide.", "cyberway divide.", "Web divide", "A");
        Question question11 = new Question("All of the following are examples of real security and privacy risks EXCEPT:", "Viruses", "Hackers", "Spam", "Identity theft.", "C");
        Question question12 = new Question("The term 'Pentium' is related to", "DVD", "Hard Disk", "Microprocessor", "Mouse", "C");
        Question question13 = new Question("What does HTTP stands for?", "Head Tail Transfer Protocol", "Hypertext Transfer Protocol", "Hypertext Transfer Plotter", "Hypertext Transfer Plot", "B");
        Question question14 = new Question("__________ is the process of dividing the disk into tracks and sectors.", "Allotting", "Crashing", "Formatting", "Tracking", "B");
        Question question15 = new Question("Which computer memory is used for storing programs and data currently being processed by the CPU ?", "Internal memory", "Mass memory", "Non-volatile memory", "PROM", "A");
        Question question16 = new Question("What type of software creates a smaller file that is faster to transfer over the Internet?", "Compression", "Fragmentation", "Encapsulation", "Unzipped", "A");
        Question question17 = new Question("WWW stands for ?", "World Whole Web", "Wide World Web", "Web World Wide", "World Wide Web", "D");
        Question question18 = new Question(" Which of the following is NOT a component of the Central Processing Unit of the computer? ", " Universal Serial Bus", "Uninterrupted Power Supply", "CU", "  Both A & B", "D");
        Question question19 = new Question("When cutting and pasting, cutting section is temporarily stored in", "Dashboard", "Clipboard", "Hard drive", "Diskette", "B");
        Question question20 = new Question("You can move between two or more Excel files opened by using the", "ctrl + tab", "ctrl + page up", "ctrl + page down", "ctrl + F9", "A");
        Question question21 = new Question("______ Is the execution of at least two different programs simultaneously,", "Multiprocessing", "Multi programming", "Recovery", "Integrity", "A");
        Question question22 = new Question("__________ Is a mechanism by which all the content in a specified storage areas are written as output.", "Scheduling", "Logging", "Chumping", "Dumping", "D");
        Question question23 = new Question("The first generation computers used ____________ for circuitry.", "Vacuum tube", "Transistors", "Integrated Circuits", "Large Scale Integration", "A");
        Question question24 = new Question("The period of First generation computers is", "1940-1956", "1940-1958", "1950-1960", "1960-2000", "A");
        Question question25 = new Question("The period of Second generation computers is", "1956-1964", "1956-1963", "1957-1970", "1958-1963", "B");
        Question question26 = new Question("The period of Third generation computers is", "1964-1974", "1964-1977", "1964-1975", "1970 Above", "C");
        Question question27 = new Question("The period of Fourth generation computers is", "1975-1995", "1975-1989", "1975-2000", "None of these", "B");
        Question question28 = new Question("The process of transferring files from a computer on the Internet to your computer is called", "Downloading", "FTP", "Forwarding", "Uploading", "A");
        Question question29 = new Question("Which of the following is an example of a binary number?", "W1BFD3", "45364", "05546", "100101", "D");
        Question question30 = new Question("VLSI technology is used in __________ generation computers.", "First", "Second", "Third", "Fourth", "D");
        Question question31 = new Question("The operating system does all of the following EXCEPT:", "provide a way for the user to interact with the computer.", "enable users to perform a specific task such as document editing.", "manage memory and storage", "manage the central processing unit (CPU)", "B");

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);
        questions.add(question13);
        questions.add(question14);
        questions.add(question15);
        questions.add(question16);
        questions.add(question17);
        questions.add(question18);
        questions.add(question19);
        questions.add(question20);
        questions.add(question21);
        questions.add(question22);
        questions.add(question23);
        questions.add(question24);
        questions.add(question25);
        questions.add(question26);
        questions.add(question27);
        questions.add(question28);
        questions.add(question29);
        questions.add(question30);
        questions.add(question31);

        Collections.shuffle(questions);

        return questions;
    }

    private ArrayList<Question> getIntermediateQuestion() {
        Question question32 = new Question("C++ is a: ", "General purpose programming", "Movie making program", "Client-side scripting language", "", "A");
        Question question33 = new Question("What is the starting point of a computer program?", "First line", "Main Function", "from  <iosstream>", "from int main()", "B");
        Question question34 = new Question("Which operator is having the highest precedence in c++?", "array subscript", "Scope resolution operator", "static_cast", "dynamic_cast", "B");
        Question question35 = new Question("subscript operator is used to access which elements?", "string", "char", "array", "all of the above", "C");
        Question question36 = new Question("What do we need to use when we have multiple subscripts?", "operator()", "operator[]", "operator", "none of the above", "A");
        Question question37 = new Question("Which container in c++ will take large objects?", "string", "class", "vector", "none of the above", "C");
        Question question38 = new Question("To distribute your java application  to different platforms, how many java versions do you need to create?", "one for each platform", "just one version", "two versions", "none of the above", "B");
        Question question39 = new Question("Which of the Following Statement is true?", "Java is used only in web angd mobile applications", "Java is used only in NASA's space related applications", "Java has a huge huge developer community", "", "C");
        Question question40 = new Question("Which method prints text in a java program?", "out.writeText()", "System.out()", "System.printText()", "System.out.println", "D");
        Question question41 = new Question("Single line coomments in java are created using", "// characters at the end of the line", "*/ characters at the begining of the line", "** characters at the beginning of the line ", "// characters at the beginnin of the line", "D");
        Question question42 = new Question("What will be the output of the program?\n" +
                "\n" +
                "public class ArrayTest \n" +
                "{ \n" +
                "    public static void main(String[ ] args)\n" +
                "    { \n" +
                "        float f1[ ], f2[ ]; \n" +
                "        f1 = new float[10]; \n" +
                "        f2 = f1; \n" +
                "        System.out.println(\"f2[0] = \" + f2[0]); \n" +
                "    } \n" +
                "}", "It prints f2[0] = 0.0", "It prints f2[0] = NaN", "An error at f2 = f1; causes compile to fail.", "It prints the garbage value.", "A");
        Question question43 = new Question("What will be the output of the program?\n" +
                "\n" +
                "int I = 0;\n" +
                "label:\n" +
                "    if (I < 2) {\n" +
                "    System.out.print(\"I is \" + I);\n" +
                "    I++;\n" +
                "    continue label;\n" +
                "}", "I is 0", "I is 0 I is 1", "Compilation fails. ", "None of the above", "C");
        Question question44 = new Question("PHP is a: ", "Server side programming language", "website", "Home page", "Markup language", "A");
        Question question45 = new Question("Which is the most widely recommended way to use PHP tags?", "<php", "<?php", "<?", "", "B");
        Question question46 = new Question("W What will be the output of the following PHP code ?\n" +
                "\n" +
                "<?php \n" +
                "$x = 75;\n" +
                "$y = 25; \n" +
                "function addition()\n" +
                "{\n" +
                "    $GLOBALS['z'] = $GLOBALS['x'] + $GLOBALS['y'];\n" +
                "}\n" +
                "addition();\n" +
                "echo $z;\n" +
                "?>", "25", "75", "100", "error", "C");
        Question question47 = new Question("Which of the following advanced OOP features is/are not supported by PHP?\n" +
                "i) Method overloading\n" +
                "ii) Multiple Inheritance\n" +
                "iii) Namespaces\n" +
                "iv) Object Cloning", "All of the mentioned", "None of the mentioned", "i and ii", "iii and iv", "C");
        Question question48 = new Question("Which version of PHP introduced the advanced concepts of OOP?", "PHP 4", "PHP 5", "PHP 5.3", "PHP 6", "B");
        Question question49 = new Question("What will be the output of the following PHP code ?\n" +
                "\n" +
                "<?php\n" +
                "echo \"This\".\"was\".\"a\".\"bad\".\"idea\";\n" +
                "?>", "This, was, a, bad, idea", "This was a bad idea", "Thiswasabadidea", "Error", "C");
        Question question50 = new Question("Which of the following is not true about a database?", "A database is a collection of data", "A database is a programming language", "A database is made up of tables", "", "B");
        Question question51 = new Question("Database tables are made up of:", "Columns only", "Rows but no columns", "columns and rows", "", "C");
        Question question52 = new Question("Whenever you run multiple queries in a database...", "you get an error because its not possible to run more than one query", "you get only the last query's result ", "each query must end with a semicolon", "", "C");
        Question question53 = new Question("SQL is: ", "case sensitive", "case insensitive", "case slightly sensitive", "case slightly insensitive", "A");
        Question question54 = new Question("The command to remove rows from a table 'CUSTOMER' is:", "REMOVE FROM CUSTOMER ...", "DROP FROM CUSTOMER ...", "DELETE FROM CUSTOMER WHERE ...", "UPDATE FROM CUSTOMER ...", "C");
        Question question55 = new Question("Which of the following is the original purpose of SQL?", "cTo specify the syntax and semantics of SQL data definition language", "To specify the syntax and semantics of SQL manipulation language", "To define the data structures", "All of the above.", "D");
        Question question56 = new Question("What is the maximum possible length of an identifier in python?", "31", "63", "79", "none of the above", "D");
        Question question57 = new Question("What is the order of precedence in python?\n" +
                "i) Parentheses\n" +
                "ii) Exponential\n" +
                "iii) Multiplication\n" +
                "iv) Division\n" +
                "v) Addition\n" +
                "vi) Subtraction", "i,ii,iii,iv,v,vi", "ii,i,iii,iv,v,vi", "ii,i,iv,iii,v,vi", " i,ii,iii,iv,vi,v", "A");
        Question question58 = new Question("Operators with the same precedence are evaluated in which manner?", "Left to Right", "Right to Left", "Can’t say", "None of the mentioned", "A");
        Question question59 = new Question("What is the output of the following?\n" +
                "\n" +
                "my_string = \"hello world\"\n" +
                "k = [(i.upper(), len(i)) for i in my_string]\n" +
                "print(k)", "[(‘HELLO’, 5), (‘WORLD’, 5)].", "[(‘H’, 1), (‘E’, 1), (‘L’, 1), (‘L’, 1), (‘O’, 1), (‘ ‘, 1), (‘W’, 1), (‘O’, 1), (‘R’, 1), (‘L’, 1), (‘D’, 1)].", "[(‘HELLO WORLD’, 11)].", "none of the mentioned", "B");
        Question question60 = new Question("What is the output of the function shown below?\n" +
                "\n" +
                "import math\n" +
                "abs(math.sqrt(25))", "Error", "-5", "5", "5.0", "D");
        Question question61 = new Question("Which of these in not a core data type in python?", "Lists", "Dictionary", "Tuples", "Class", "D");

        questions.add(question32);
        questions.add(question33);
        questions.add(question34);
        questions.add(question35);
        questions.add(question36);
        questions.add(question37);
        questions.add(question38);
        questions.add(question39);
        questions.add(question40);
        questions.add(question41);
        questions.add(question42);
        questions.add(question43);
        questions.add(question44);
        questions.add(question45);
        questions.add(question46);
        questions.add(question47);
        questions.add(question48);
        questions.add(question49);
        questions.add(question50);
        questions.add(question51);
        questions.add(question52);
        questions.add(question53);
        questions.add(question54);
        questions.add(question55);
        questions.add(question56);
        questions.add(question57);
        questions.add(question58);
        questions.add(question59);
        questions.add(question60);
        questions.add(question61);

        Collections.shuffle(questions);

        return questions;
    }

    private ArrayList<Question> getAdvancedQuestion() {
        Question question62 = new Question("Choose the correct one.  \n" +
                "\n" +
                "int[] numbers = { 9, 10, 0, 11 }; \n" +
                "  \n" +
                "        var nums = \n" +
                "        from n in numbers \n" +
                "        select n + 1; \n" +
                "  \n" +
                "    foreach (var i in nums) \n" +
                "    { \n" +
                "        Console. Write (i+”,”); \n" +
                "    } ", "10, 11, 1, 12", "10, 11, 12, 13", "9, 10, 0, 11 ", "None of these", "A");
        Question question63 = new Question("Which LINQ statement defines the range variable in a LINQ query?", "from", "select", "join", "where", "A");
        Question question64 = new Question("Which query expression is used to limit the number of results?", "Skip", "Take", "Where", "Select", "b");
        Question question65 = new Question("What LINQ expressions are used to shape results in a query?\n" +
                "\n" +
                "1.\twhere\n" +
                "2.\tselect\n" +
                "3.\tjoin\n" +
                "4.\tgroup", "2, 4", "1, 2", "3, 4", "None", "A");
        Question question66 = new Question(" What types of shapes can LINQ query results be shaped into?\n" +
                "\n" +
                "1.\tCollections of primitive types\n" +
                "2.\tCollections of complex types\n" +
                "3.\tSingle types\n" +
                "4.\tCollections of anonymous types", "1, 2, 4", "1,2,3", "1,3,4 ", "None of the above", "A");
        Question question67 = new Question("Which of the following statements is true?", "LINQ to SQL works with any database.", "LINQ to SQL works with SQL Server.", "LINQ to SQL is a CLR feature.", "LINQ to SQL is a SQL Server feature.", "b");
        Question question68 = new Question("What is lamda expression?\n" +
                "\n" +
                "1.\tAnonymous function\n" +
                "2.\tCan  be used to create delegates\n" +
                "3.\tNamed function\n" +
                "4.\tNone\n", "1, 2", "1, 2, 3", "1, 3", "4", "A");
        Question question69 = new Question("Choose the correct option.", "Dynamic type is non - static type.", "Dynamic type is static type.", "Implicit conversion does not work with type dynamic.", "None of the above", "b");
        Question question70 = new Question("What types of Objects can you query using LINQ?", "DataTables and DataSets", "Any .NET Framework collection that implements IEnumerable(T)", "Collections that implement interfaces that inherit from IEnumerable(T)", "All of the above", "D");
        Question question71 = new Question("Trace the output\n" +
                "namespace A\n" +
                "{\n" +
                "    class MyClass\n" +
                "    {\n" +
                "        public void fun()\n" +
                "        {\n" +
                "            Console.WriteLine( \" C # is fun \" );\n" +
                "        }\n" +
                "    }\n" +
                "    namespace B\n" +
                "    {\n" +
                "        class MyClass\n" +
                "        {\n" +
                "            public void fun()\n" +
                "            {\n" +
                "                Console.WriteLine( \" C # is interesting \" );\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "Consider the above code what will be the output of following program\n" +
                "class Program\n" +
                "    {\n" +
                "        static void Main(string[] args)\n" +
                "        {\n" +
                "            A.B.MyClass obj = new A.B.MyClass();\n" +
                "\n" +
                "            obj.fun();\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    }\n", "C # is interesting", "C # is fun", "compile time error", "None of the above", "A");
        Question question72 = new Question("An interface can contain declaration of?", "Static members", "Methods", "Methods, properties, events, indexers", "All of the above", "c");
        Question question73 = new Question("Which of the following are correct?\n" +
                "\n" +
                "1.\tAn interface can be instantiated directly.\n" +
                "2.\tInterfaces can contain constructor.\n" +
                "3.\tInterfaces contain no implementation of methods.\n" +
                "4.\tClasses and structs can implement more than one interface.\n" +
                "5.\tAn interface itself can inherit from multiple interfaces.", "2,3,4", "1,2,3", "3,4,5", "All of the above", "c");
        Question question74 = new Question("Which of the following are correct?\n" +
                "\n" +
                "1.\tDelegates are like C++ function pointers.\n" +
                "2.\tDelegates allow methods to be passed as parameters.\n" +
                "3.\tDelegates can be used to define callback methods.\n" +
                "4.\tDelegates are not type safe.", "1,3,4", "1,2,3", "2,3,4", "None of the above", "B");
        Question question75 = new Question("Properties in .NET can be declare as\n" +
                "\n" +
                "1.\tStatic, Protected internal, Virtual\n" +
                "2.\tPublic, internal, Protected internal\n" +
                "3.\tOnly public \n" +
                "4.\tNone\n", "1, 2", "3", "1, 2, 3", "4", "A");
        Question question76 = new Question("The best way for handling exception when dealing with a database connection?", "Implement a try / catch block that catches System.Exceptions.", "Implementing custom error page.", "Implement a try / catch block that catches individual exception types, such as SQLException.", "Display an error message.", "A");
        Question question77 = new Question("Reflection can be used when?", "To create type at compile time", "To access attributes at compile time.", "To access attributes in your program's metadata", "None of the above", "C");
        Question question78 = new Question("Choose the correct one\n" +
                "\n" +
                "1)\tXML serialization serializes the public fields and properties of an object.\n" +
                "2)\tXML serialization serializes the private fields and properties of an object.\n" +
                "3)\tXML serialization serializes only the public methods.\n", "Only 1 & 2", "Only 1", "Only 2", "All of the above", "B");
        Question question79 = new Question("An indexer is ", "A class", "A structure", "An enumeration", "A special type of property", "D");
        Question question80 = new Question("A write - only property can be specified if the following is present.", "None of the modifiers", "The get modifier only", "The set modifier only", "Both the modifiers", "B");
        Question question81 = new Question(" Choose the correct one.  \n" +
                "\n" +
                "int[] numbers = { 5, 4, 11, 3, 9, 8, 6, 7, 2, 0 };  \n" +
                "  \n" +
                "    var nums = numbers.Skip(4);    \n" +
                "  \n" +
                "    foreach (var n in nums)   \n" +
                "    {   \n" +
                "        Console.Write(n+”  “);   \n" +
                "    } \n", "9867 2 0", "5411398", "54113", "None of the above", "A");
        Question question82 = new Question("Which interface defines the basic extension methods for LINQ?\n", "IComparable<T>", "IList", "IEnumerable", "IQueryable<T>", "C");
        Question question83 = new Question("How you can merge the results from two separate LINQ queries into a single result set.", "Use the ToList method.", "Use the DataContractJsonSerializer class.", "Use the XElement class.", "Use the Concat method.", "D");
        Question question84 = new Question("Which of the following objects represents a LINQ to SQL O / R map?", "DataSet", "XElement", "DataContext", "ObjectContext", "C");
        Question question85 = new Question("Which of the following statement is correct?", "Anonymous types are class types that derive directly from object.", "Anonymous types are not class types that derive directly from object.", "Anonymous types are class types that derive directly from System.Class.", "None of the above", "B");
        Question question86 = new Question("Which of the following statement / s is / are true?", "CommitChanges is a method of SqlDataContext.", "CommitChanges is a method of DataContext.", "CommitChanges is a method of DbDataContext.", "CommitChanges is a method of OleDbData Context", "B");
        Question question87 = new Question("Which Data Provider gives the maximum performance when connect to SQL Server?", "The SqlClient data provider.", "The OLE DB data provider.", "The Oracle data provider", "All of the above.", "A");
        Question question88 = new Question("What property do you modify on a server control to minimize the size of the ViewState data?", "ViewState=”true”", "Set EnableViewState to false", "Set EnableViewState to true", "None of the above", "B");
        Question question89 = new Question("What are the client-side state management options that ASP.NET supports?", "Application", "Session", "Querystring", "Option a and b are correct", "C");
        Question question90 = new Question("Which Session Mode stores session Information in Current Application Domain", "InProc", "StateServer", "SQLServer", "Off", "A");
        Question question91 = new Question("By default, when you use Page Output Caching, at what location page is cached?", "Only on web server", "Only on Client", "Web server, any proxy servers, and browser", "All of the above.", "C");

        questions.add(question62);
        questions.add(question63);
        questions.add(question64);
        questions.add(question65);
        questions.add(question66);
        questions.add(question67);
        questions.add(question68);
        questions.add(question69);
        questions.add(question70);
        questions.add(question71);
        questions.add(question72);
        questions.add(question73);
        questions.add(question74);
        questions.add(question75);
        questions.add(question76);
        questions.add(question77);
        questions.add(question78);
        questions.add(question79);
        questions.add(question80);
        questions.add(question81);
        questions.add(question82);
        questions.add(question83);
        questions.add(question84);
        questions.add(question85);
        questions.add(question86);
        questions.add(question87);
        questions.add(question88);
        questions.add(question89);
        questions.add(question90);
        questions.add(question91);







        Collections.shuffle(questions);

        return questions;
    }
}
