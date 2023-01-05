import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {

    JFrame frame;

    JTextArea jTextArea;

    JMenuBar jMenuBar;

    JMenu File;

    JMenu Edit;

    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;

    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;

    JMenuItem CloseEditor;
    private Object JFileChooser;

    //Created Constructor
    SimpleTextEditor()
    {
        //Created the frame
        frame = new JFrame("Simple Text Editor");
        //Set frame size
        frame.setBounds(0, 0, 1000, 800);
        //Created Text Area
        jTextArea = new JTextArea("Welcome To The Simple Text Editor");
        //Created Menu Bar
        jMenuBar = new JMenuBar();

        //Created Menu
        Close = new JMenu("Close");
        Edit = new JMenu("Edit");
        File = new JMenu("File");

        //Added Menu into MenuBar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        //Created MenuItem into Menu(File)
        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);
        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);
        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);

        //Added MenuItem into Menu(File)
        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);

        //Created MenuItem into Menu(Edit)
        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);
        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);
        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);

        //Adding MenuItem into Menu(Edit)
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        //Created MenuItem into Menu(Close)
        CloseEditor = new JMenuItem("Close");
        CloseEditor.addActionListener(this);

        //Added MenuItem into Menu(Close)
        Close.add(CloseEditor);

        //Added MenuBar into Menu
        frame.setJMenuBar(jMenuBar);

        //Added Text Area
        frame.add(jTextArea);

        // Exit Button
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //To visible our frame
        frame.setVisible(true);
    }

    //Made main class
    public static void main(String[] args){
        //Created object to call constructor
        SimpleTextEditor f = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy"))
        {
            jTextArea.copy();
        }
        else if (s.equals("Cut"))
        {
            jTextArea.cut();
        }
        else if (s.equals("Paste"))
        {
            jTextArea.paste();
        }
        else if (s.equals("Print"))
        {
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
        else if (s.equals("New"))
        {
            jTextArea.setText("");
        }
        else if (s.equals("Close"))
        {
            frame.setVisible(false);
        }
        else if (s.equals("Open"))
        {
            JFileChooser jFileChooser= new JFileChooser("C: ");

            int ans = jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1= bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        else if(s.equals("Save"))
        {
            JFileChooser jFileChooser = new JFileChooser("C: ");
            int ans = jFileChooser.showSaveDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                    writer.write(jTextArea.getText());
                    //flush the writer
                    writer.flush();
                    //closing
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
