package es.unileon.ulebank.assets.gui;

import javax.swing.JPanel;

import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.assets.strategy.commission.exception.CommissionException;

/**
 *
 * @author amdiaz8
 */
public class AssetsFrame extends javax.swing.JFrame {

	private javax.swing.JPanel jPanel1;
	private Model model;

	/**
	 * Creates new form AssetsFrame
	 */
	public AssetsFrame(Model model) {
		this.model = model;
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(
				new javax.swing.BoxLayout(getContentPane(),
						javax.swing.BoxLayout.LINE_AXIS));
		getContentPane().add(jPanel1);

		pack();
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AssetsFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AssetsFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AssetsFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AssetsFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Model model;
				try {
					model = Model.getInstance();
					AssetsFrame frame = new AssetsFrame(model);
					frame.setVisible(true);
					AssetsInitPanel panel = new AssetsInitPanel(frame);
					frame.changePanel(panel);
				} catch (LINCMalformedException | CommissionException e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void changePanel(JPanel newPanel) {
		remove(this.jPanel1);
		this.jPanel1 = newPanel;
		this.jPanel1.setVisible(true);
		add(this.jPanel1);
		repaint();
		pack();
	}

	public Model getModel() {
		return this.model;
	}

}
