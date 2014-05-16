package es.unileon.ulebank.assets.gui;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

/**
 *
 * @author amdiaz8
 */
public class AssetsLoanPaymentsPanel extends javax.swing.JPanel {
	private AssetsFrame frame;
	private Model model;
	private PaymentsLoanModel modelPayments;

	private javax.swing.JButton jButtonBack;
	private javax.swing.JList jList1;
	private javax.swing.JScrollPane jScrollPane1;

	/**
	 * Creates new form AssetsLoanPayments
	 */
	public AssetsLoanPaymentsPanel(AssetsFrame frame) {
		this.modelPayments = new PaymentsLoanModel();
		this.frame = frame;
		this.model = this.frame.getModel();

		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jButtonBack = new javax.swing.JButton();

		setBorder(javax.swing.BorderFactory.createTitledBorder("Loan payments"));
		setLayout(new java.awt.GridBagLayout());

		jList1.setModel(modelPayments);
		jScrollPane1.setViewportView(jList1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(jScrollPane1, gridBagConstraints);

		jButtonBack.setText("Come back ");
		jButtonBack.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jButtonBack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonBackActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		add(jButtonBack, gridBagConstraints);
	}

	private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
		this.frame.changePanel(new AssetsInitPanel(this.frame));
	}

	public void addPayment(ScheduledPayment payment) {
		this.modelPayments.addPayment(payment);
	}

}
