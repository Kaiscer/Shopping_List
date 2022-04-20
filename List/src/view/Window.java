	package view;

	import javax.swing.JFrame;

	import java.awt.Color;
	import java.awt.Font;


	import javax.swing.JTextField;
	import javax.swing.SpinnerNumberModel;

	import control.Controller;
	import model.DataBase;
	import model.Producto;

	import javax.swing.JLabel;
	import javax.swing.JSpinner;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.DefaultListModel;
	import javax.swing.JComboBox;
	import javax.swing.JButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JList;
	import javax.swing.ListSelectionModel;
	import javax.swing.ImageIcon;

	public class Window extends JFrame {
		
		public static final String BTN_ADD = "Añadir";
		public static final String BTN_DLT = "Eliminar";
		public static final String BTN_BUY = "Comprar";
		
		
		private JTextField txtName;
		private JSpinner spnCantidad;
		private JComboBox<String> cmbUnidades;
		private JButton btnAdd;
		private JLabel lblError;
		private JScrollPane scrollLits;
		private JList<Producto> JlistProd;
		private JScrollPane scrollPane;
		private JTextArea txtList;
		private JButton btnBuy;
		private JButton btnDelete;
		
		
		private DataBase listaProductos;
		
		
		public Window() {
			initComponents();
			
			listaProductos = new DataBase();
		}

		private void initComponents() {
			getContentPane().setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			setTitle("Lista de Compra");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			
			JLabel lblnameProd = new JLabel(Producto.NAME_PROD);
			lblnameProd.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			lblnameProd.setBounds(6, 37, 171, 16);
			getContentPane().add(lblnameProd);
			
			txtName = new JTextField();
			txtName.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			txtName.setBounds(279, 33, 215, 26);
			getContentPane().add(txtName);
			txtName.setColumns(10);
			
			JLabel lblCantidad = new JLabel(Producto.CANT_PROD);
			lblCantidad.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			lblCantidad.setBounds(6, 98, 78, 16);
			getContentPane().add(lblCantidad);
			
			spnCantidad = new JSpinner();
			spnCantidad.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			spnCantidad.setEditor(new JSpinner.DefaultEditor(spnCantidad));
			spnCantidad.setModel(new SpinnerNumberModel(0,0,500,1));
			spnCantidad.setBounds(279, 93, 58, 26);
			getContentPane().add(spnCantidad);
			
			JLabel lblUnidad = new JLabel(Producto.UNID_PROD);
			lblUnidad.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			lblUnidad.setBounds(6, 158, 64, 16);
			getContentPane().add(lblUnidad);
			
			cmbUnidades = new JComboBox<String>();
			cmbUnidades.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 15));
			cmbUnidades.setModel(new DefaultComboBoxModel<String>(Producto.MEDIDA_UNID));		
			cmbUnidades.setBounds(288, 147, 93, 27);
			getContentPane().add(cmbUnidades);
			
			btnAdd = new JButton("");
			btnAdd.setIcon(new ImageIcon(Window.class.getResource("/img/buy.png")));
			btnAdd.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
			btnAdd.setBounds(349, 186, 64, 44);
			getContentPane().add(btnAdd);
			
			lblError = new JLabel("");
			lblError.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
			lblError.setBounds(106, 65, 372, 16);
			getContentPane().add(lblError);
			
			scrollLits = new JScrollPane();
			scrollLits.setBounds(6, 250, 162, 257);
			getContentPane().add(scrollLits);
			
			JlistProd = new JList<Producto>();
			JlistProd.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
			JlistProd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollLits.setViewportView(JlistProd);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(310, 250, 184, 257);
			
			getContentPane().add(scrollPane);
			
			txtList = new JTextArea();
			txtList.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
			scrollPane.setViewportView(txtList);
			
			btnBuy = new JButton("");
			btnBuy.setIcon(new ImageIcon(Window.class.getResource("/img/button.png")));
			btnBuy.setBounds(202, 342, 58, 44);
			getContentPane().add(btnBuy);
			
			btnDelete = new JButton("");
			btnDelete.setIcon(new ImageIcon(Window.class.getResource("/img/delete-button.png")));
			btnDelete.setBounds(40, 515, 69, 51);
			getContentPane().add(btnDelete);
			
			
			
			setSize(500,600);
			setLocationRelativeTo(null);
		}
		
		public void hacerVisible() {
			setVisible(true);
		}

		public void setControl(Controller ctrl) {
			btnAdd.addActionListener(ctrl);
			btnDelete.addActionListener(ctrl);
			btnBuy.addActionListener(ctrl);
			
			
		}

		public JButton getBtnAdd() {
			
			return btnAdd;
		}
		
		public JButton getBtnDlt() {
			
			return btnDelete;
		}
		
		public JButton getBtnBuy() {
			
			return btnBuy;
		}

		public void addProducts() {
			
			String name = txtName.getText().toLowerCase();
			if (name.isEmpty()) {
				setError("Debes ingresar el nombre del producto");
				return;
			}
			int cant =(int)spnCantidad.getValue();
			if (cant == 0 || cant > 500) {
				setError("Ingresa la cantidad");
				return;
			}
			String und = (String) cmbUnidades.getSelectedItem();
			
			Producto product = new Producto(name, cant, und);
			
			if (search(name) == -1) {
				listaProductos.addProducto(product);
				clearError();
				clearForm();
				updateList();
			}else {
				setError("El producto ya existe");
			}
			
		}

		private void updateList() {
			DefaultListModel<Producto> model = new DefaultListModel<Producto>();
			
			for (Object prod : listaProductos.getProductos()) {
				model.addElement((Producto)prod);
			}
				JlistProd.setModel(model);
				scrollLits.setViewportView(JlistProd);
			
		}

		private void clearForm() {
			txtName.setText("");
			spnCantidad.setValue(0);
			cmbUnidades.setSelectedItem(Producto.MEDIDA_UNID[0]);
			
		}

		private void clearError() {
			setError("");
			
		}

		private int search(String name) {
			int pos = -1;
			
			for (int i = 0; i < listaProductos.getListaCompra().size(); i++) {
				
				if (name.equals(listaProductos.getListaCompra().get(i).getNombre())) {
					pos = i;
				}
			}
			
			return pos;
		}

		private void setError(String error) {
			if (error == null || error.isEmpty()) {
				lblError.setText("");
			}else {
				lblError.setText(error);
				lblError.setForeground(Color.RED);
			}
			
		}

		

		public void showBuy() {
			String products ="";
			
			for (Producto product : listaProductos.getListaCompra()) {
				
				products +=  product;
			}
			
			updateList();
			clearError();
			clearForm();
			
			txtList.setText("Tu compra es la sigiente: \n" + products);
			
			DefaultListModel<Producto> model = new DefaultListModel<Producto>();
			JlistProd.setModel(model);
			
			
		}

		public void DeleteProducts() {
			
			if (JlistProd.getSelectedIndex() == -1) {
				setError("Selecciona el producto que deseas eliminar");
				return;
			}
			listaProductos.deleteProducto(JlistProd.getSelectedIndex());
			updateList();
			clearError();
			clearForm();
			
		}

		

		
}



