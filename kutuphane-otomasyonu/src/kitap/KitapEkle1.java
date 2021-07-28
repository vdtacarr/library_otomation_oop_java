package kitap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

import nesne.Kitap;
import nesne.Tur;
import nesne.Yazar;

import sql.Baglan;
import sql.DBKitap;
import sql.DBTur;
import sql.DBYazar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KitapEkle1 extends JFrame {

	private JFrame frmKitapEkle;
	private JTextField jTextAdi;
	private JTextField jTextBarkod;
	private JTextField jTextSayfaSayisi;
	private JTextField jTextBaski;
	private JTextField jTextStok;
	private JComboBox<Tur> comboBoxTur;
	private JComboBox<Yazar> comboBoxYazar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapEkle1 window = new KitapEkle1();
					window.frmKitapEkle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KitapEkle1() {
		super("Another GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initialize();
        pack();
        setVisible(true);
 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKitapEkle = new JFrame();
		frmKitapEkle.setTitle("Kitap Ekle");
		frmKitapEkle.setResizable(false);
		frmKitapEkle.setBounds(100, 100, 314, 277);
		frmKitapEkle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKitapEkle.getContentPane().setLayout(null);
		
		jTextAdi = new JTextField();
		jTextAdi.setBounds(104, 11, 115, 20);
		frmKitapEkle.getContentPane().add(jTextAdi);
		jTextAdi.setColumns(10);
		
		jTextBarkod = new JTextField();
		jTextBarkod.setColumns(10);
		jTextBarkod.setBounds(104, 37, 115, 20);
		frmKitapEkle.getContentPane().add(jTextBarkod);
		
		jTextSayfaSayisi = new JTextField();
		jTextSayfaSayisi.setColumns(10);
		jTextSayfaSayisi.setBounds(104, 62, 115, 20);
		frmKitapEkle.getContentPane().add(jTextSayfaSayisi);
		
		jTextBaski = new JTextField();
		jTextBaski.setColumns(10);
		jTextBaski.setBounds(104, 88, 115, 20);
		frmKitapEkle.getContentPane().add(jTextBaski);
		
		jTextStok = new JTextField();
		jTextStok.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
				
			}
		});
		jTextStok.setColumns(10);
		jTextStok.setBounds(104, 114, 115, 20);
		frmKitapEkle.getContentPane().add(jTextStok);
		
		JLabel lblAd = new JLabel("Ad\u0131 :");
		lblAd.setBounds(10, 17, 72, 14);
		frmKitapEkle.getContentPane().add(lblAd);
		
		JLabel lblBarkod = new JLabel("Barkod :");
		lblBarkod.setBounds(10, 43, 72, 14);
		frmKitapEkle.getContentPane().add(lblBarkod);
		
		JLabel lblSayfaSays = new JLabel("Sayfa say\u0131s\u0131 : ");
		lblSayfaSays.setBounds(10, 68, 84, 14);
		frmKitapEkle.getContentPane().add(lblSayfaSays);
		
		JLabel lblBaskSays = new JLabel("Bask\u0131 : ");
		lblBaskSays.setBounds(10, 91, 84, 14);
		frmKitapEkle.getContentPane().add(lblBaskSays);
		
		JLabel lblStok = new JLabel("Stok : ");
		lblStok.setBounds(10, 117, 84, 14);
		frmKitapEkle.getContentPane().add(lblStok);
		
		JLabel lblYazar = new JLabel("Yazar : ");
		lblYazar.setBounds(10, 143, 84, 14);
		frmKitapEkle.getContentPane().add(lblYazar);
		
		JLabel lblTr = new JLabel("T\u00FCr : ");
		lblTr.setBounds(10, 172, 84, 14);
		frmKitapEkle.getContentPane().add(lblTr);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = jTextAdi.getText();
				String barkod = jTextBarkod.getText();
				String sayfaSayisi = jTextSayfaSayisi.getText();
				String baski = jTextBaski.getText();
				int stok = Integer.parseInt(jTextStok.getText());
				Tur tur = (Tur)comboBoxTur.getSelectedItem();
				Yazar yazar = (Yazar)comboBoxYazar.getSelectedItem();
				
				
				Baglan baglan = Baglan.getInstance();
				DBKitap dbKitap = new DBKitap();
				
				Kitap kitap = new Kitap(ad,barkod,sayfaSayisi,baski,stok,tur.getId(),yazar.getId());
				
				dbKitap.ekle(baglan.getStmt(), kitap);
				JOptionPane.showMessageDialog(frmKitapEkle, "Eklendi");
				frmKitapEkle.dispatchEvent(new WindowEvent(frmKitapEkle, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnKaydet.setBounds(104, 214, 89, 23);
		frmKitapEkle.getContentPane().add(btnKaydet);
		
		Baglan baglan = Baglan.getInstance();
		DBYazar dbYazar = new DBYazar();
		ArrayList<Yazar> yazarlar = dbYazar.listele(baglan.getStmt());
		
		comboBoxYazar = new JComboBox<Yazar>();
		comboBoxYazar.setBounds(104, 140, 115, 20);
		frmKitapEkle.getContentPane().add(comboBoxYazar);
		for (Yazar yazar : yazarlar) {
			comboBoxYazar.addItem(yazar);
		}
		
		comboBoxTur = new JComboBox<Tur>();
		comboBoxTur.setBounds(104, 169, 115, 20);
		frmKitapEkle.getContentPane().add(comboBoxTur);
		
		DBTur dbTur = new DBTur();
		ArrayList<Tur> turler = dbTur.listele(baglan.getStmt());
		
		for (Tur tur : turler) {
			comboBoxTur.addItem(tur);
		}
	}
}
