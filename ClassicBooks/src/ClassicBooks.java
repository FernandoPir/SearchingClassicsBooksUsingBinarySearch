 /**
 * Programmer: Fernando Pires Rui
 * Date: 18/06/2021
 * Name: Airline Points
 * This program will execute a linear and binary search to find the reference of a children's book and display the title on the screen
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ClassicBooks extends JFrame {

	private JPanel contentPane;
	private JTextField LSearch;
	private JTextField BSearch;
	private JTextField Reference;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassicBooks frame = new ClassicBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public ClassicBooks() {
		
		/**
		 * Creating a BufferedReader to read the text from the file and store it on the bookList array.
		 */
		// unit 3.3 content
		ArrayList <String> books = new ArrayList <String>();
		ArrayList <Integer> nums = new ArrayList <Integer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("BookList.txt"));
			String word;
			int count = 0;
			while ((word = br.readLine()) != null ){
				if (count ==0) {
					nums.add(Integer.parseInt(word));
				}
				books.add(word);
				count++;
				
			}
		} catch (IOException Io) {
			Io.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String [] bookList = new String[books.size()];
		books.toArray(bookList);
		
		// Storing only the numbers in one array
		int count = 0;
		int [] numList = new int[books.size() / 2];
		for (int c = 0; c < (books.size() / 2) ; c++) {
			numList[c] = Integer.parseInt(bookList[count]);
			count +=2;
		
		}
		
		// Storing only the strings in the array
		int count1 = 1;
		String [] strList = new String[books.size() / 2];
		for (int c = 0; c < (books.size() / 2) ; c++) {
			strList[c] = bookList[count1];
			count1 +=2;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Children's Classics");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(156, 24, 184, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("This program will find the title of a book according to the library reference number.");
		lblNewLabel_1.setBounds(10, 73, 472, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("The program will use a Binary Search and a Linear Search.");
		lblNewLabel_2.setBounds(39, 98, 341, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter the reference  #:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(60, 140, 184, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Linear Search:");
		lblNewLabel_4.setBounds(10, 239, 93, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Binary Search:");
		lblNewLabel_5.setBounds(10, 276, 93, 14);
		contentPane.add(lblNewLabel_5);
		
		LSearch = new JTextField();
		LSearch.setEditable(false);
		LSearch.setBackground(Color.WHITE);
		LSearch.setBounds(113, 233, 341, 20);
		contentPane.add(LSearch);
		LSearch.setColumns(10);
		
		BSearch = new JTextField();
		BSearch.setBackground(Color.WHITE);
		BSearch.setEditable(false);
		BSearch.setBounds(113, 273, 341, 20);
		contentPane.add(BSearch);
		BSearch.setColumns(10);
		
		Reference = new JTextField();
		Reference.setBounds(254, 137, 86, 20);
		contentPane.add(Reference);
		Reference.setColumns(10);
		
		JButton btnNewButton = new JButton("Find It!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ref = 0;
				try {
					ref = Integer.parseInt(Reference.getText());
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "The reference must be a number");
					return;
				}
				
				LSearch.setText(linearSearch(bookList, ref));
				
				BSearch.setText(binarySearch(ref, numList,0, numList.length -1 , strList));
	
			}
		});
		btnNewButton.setBounds(178, 179, 80, 23);
		contentPane.add(btnNewButton);
	}
	
	/**
	 * This method will find the title of the book using a linear search
	 */
	public String linearSearch(String[] list, int num) {
		String ref = Integer.toString(num);
		for (int c = 0; c < list.length -1; c++) {
			if (list[c].equals(ref)) {
				return list[c + 1];
			}
		}
		return "no book found";
	}
	
	/**
	 * This method will find the title of the book using a binary search
	 */
    public static String binarySearch(int x,int[] num, int left, int right, String [] V){
        int middle;
        if (left > right) {
            return "No book found";
        }
        
        middle = (left + right)/2;
        if (num[middle] == x) {
        	return V[middle];
        	
        }
       
        if (num[middle] > x) {
            return binarySearch(x, num, left, middle-1, V);
        } else {
            return binarySearch(x,num, middle + 1, right, V);
        }  
    }
}
