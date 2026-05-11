import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionListener;

public class JSandwich extends JFrame {
    
    private JList<String> ingredientList;
    private JList<String> breadList;
    private JLabel priceLabel;
    private JButton calculateButton;
    
    private final double[] ingredientPrices = {5.99, 6.49, 4.99};
    
    public JSandwich() {
        setTitle("Sublime Sandwich Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Ingredients Panel
        JPanel ingredientPanel = new JPanel(new BorderLayout(0, 10));
        ingredientPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Main Ingredient"));
        
        String[] ingredients = {
            "Grilled Chicken - $5.99",
            "Tuna Salad - $6.49",
            "Veggie Delight - $4.99"
        };
        
        ingredientList = new JList<>(ingredients);
        ingredientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ingredientList.setSelectedIndex(0);
        JScrollPane ingredientScroll = new JScrollPane(ingredientList);
        
        ingredientPanel.add(new JLabel("Main Filling:"), BorderLayout.NORTH);
        ingredientPanel.add(ingredientScroll, BorderLayout.CENTER);
        
        // Bread Panel
        JPanel breadPanel = new JPanel(new BorderLayout(0, 10));
        breadPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Bread"));
        
        String[] breads = {
            "Classic White",
            "Whole Wheat",
            "Marble Rye",
            "Sourdough"
        };
        
        breadList = new JList<>(breads);
        breadList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        breadList.setSelectedIndex(0);
        JScrollPane breadScroll = new JScrollPane(breadList);
        
        breadPanel.add(new JLabel("Bread Type:"), BorderLayout.NORTH);
        breadPanel.add(breadScroll, BorderLayout.CENTER);
        
        mainPanel.add(ingredientPanel);
        mainPanel.add(breadPanel);
        
        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        calculateButton = new JButton("Show Price");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        priceLabel = new JLabel("Price: $0.00");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(new Color(0, 102, 0));
        
        bottomPanel.add(calculateButton);
        bottomPanel.add(priceLabel);
        
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Event Listeners
        calculateButton.addActionListener(e -> calculatePrice());
        
        ListSelectionListener listener = e -> {
            if (!e.getValueIsAdjusting()) calculatePrice();
        };
        
        ingredientList.addListSelectionListener(listener);
        breadList.addListSelectionListener(listener);
        
        calculatePrice();
    }
    
    private void calculatePrice() {
        int ingIndex = ingredientList.getSelectedIndex();
        double price = (ingIndex >= 0) ? ingredientPrices[ingIndex] : 0.0;
        priceLabel.setText(String.format("Price: $%.2f", price));
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        SwingUtilities.invokeLater(() -> new JSandwich().setVisible(true));
    }
}