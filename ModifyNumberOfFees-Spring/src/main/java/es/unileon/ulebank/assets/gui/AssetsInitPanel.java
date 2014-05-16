package es.unileon.ulebank.assets.gui;

import java.util.ArrayList;
import java.util.Date;

import es.unileon.ulebank.assets.KindOfMethod;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.assets.strategy.commission.PercentCommission;
import es.unileon.ulebank.assets.strategy.commission.exception.CommissionException;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.support.PaymentPeriod;

/**
 *
 * @author amdiaz8
 */
public class AssetsInitPanel extends javax.swing.JPanel {

	private AssetsFrame frame;
	private Model model;

	private javax.swing.ButtonGroup buttonGroupLoanOption;
	private javax.swing.ButtonGroup buttonGroupMethodAmortization;
	private javax.swing.JButton jButtonCalculate;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabelCancelCommission;
	private javax.swing.JLabel jLabelModifyCommission;
	private javax.swing.JLabel jLabelOpeningCommission;
	private javax.swing.JLabel jLabelStudyCommission;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JRadioButton jRadioButtonAddToDebt;
	private javax.swing.JRadioButton jRadioButtonPayAtCounter;
	private javax.swing.JSlider jSliderCancelCommission;
	private javax.swing.JSlider jSliderModifyCommission;
	private javax.swing.JSlider jSliderOpeningCommission;
	private javax.swing.JSlider jSliderStudyCommission;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JPanel panelCommissions;
	private javax.swing.JPanel panelLoanData;
	private javax.swing.JPanel panelLoanOptions;
	private javax.swing.JPanel panelMethodAmortization;
	private javax.swing.JRadioButton radioButtonAmerican;
	private javax.swing.JRadioButton radioButtonFrench;
	private javax.swing.JRadioButton radioButtonGerman;
	private javax.swing.JRadioButton radioButtonItalian;
	private javax.swing.JRadioButton radioButtonProgressive;

	public AssetsInitPanel(AssetsFrame frame) {
		this.frame = frame;
		initComponents();
		this.model = this.frame.getModel();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel6 = new javax.swing.JPanel();
		buttonGroupMethodAmortization = new javax.swing.ButtonGroup();
		buttonGroupLoanOption = new javax.swing.ButtonGroup();
		jButtonCalculate = new javax.swing.JButton();
		panelMethodAmortization = new javax.swing.JPanel();
		radioButtonFrench = new javax.swing.JRadioButton();
		radioButtonItalian = new javax.swing.JRadioButton();
		radioButtonAmerican = new javax.swing.JRadioButton();
		radioButtonGerman = new javax.swing.JRadioButton();
		radioButtonProgressive = new javax.swing.JRadioButton();
		panelLoanData = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jComboBox2 = new javax.swing.JComboBox();
		panelLoanOptions = new javax.swing.JPanel();
		jRadioButtonAddToDebt = new javax.swing.JRadioButton();
		jRadioButtonPayAtCounter = new javax.swing.JRadioButton();
		panelCommissions = new javax.swing.JPanel();
		jLabelCancelCommission = new javax.swing.JLabel();
		jLabelModifyCommission = new javax.swing.JLabel();
		jLabelOpeningCommission = new javax.swing.JLabel();
		jLabelStudyCommission = new javax.swing.JLabel();
		jSliderCancelCommission = new javax.swing.JSlider();
		jSliderModifyCommission = new javax.swing.JSlider();
		jSliderOpeningCommission = new javax.swing.JSlider();
		jSliderStudyCommission = new javax.swing.JSlider();

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
				Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
				Short.MAX_VALUE));

		setLayout(new java.awt.GridBagLayout());

		jButtonCalculate.setText("Calculate payments");
		jButtonCalculate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCalculateActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 20;
		add(jButtonCalculate, gridBagConstraints);

		panelMethodAmortization.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Method of amortization"));
		panelMethodAmortization.setLayout(new java.awt.GridBagLayout());

		buttonGroupMethodAmortization.add(radioButtonFrench);
		radioButtonFrench.setMnemonic('F');
		radioButtonFrench.setText("French method");
		radioButtonFrench
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						radioButtonFrenchActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelMethodAmortization.add(radioButtonFrench, gridBagConstraints);

		buttonGroupMethodAmortization.add(radioButtonItalian);
		radioButtonItalian.setMnemonic('I');
		radioButtonItalian.setText("Italian method");
		radioButtonItalian
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						radioButtonItalianActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelMethodAmortization.add(radioButtonItalian, gridBagConstraints);

		buttonGroupMethodAmortization.add(radioButtonAmerican);
		radioButtonAmerican.setMnemonic('A');
		radioButtonAmerican.setText("American method");
		radioButtonAmerican
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						radioButtonAmericanActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelMethodAmortization.add(radioButtonAmerican, gridBagConstraints);

		buttonGroupMethodAmortization.add(radioButtonGerman);
		radioButtonGerman.setMnemonic('G');
		radioButtonGerman.setText("German method");
		radioButtonGerman
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						radioButtonGermanActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelMethodAmortization.add(radioButtonGerman, gridBagConstraints);

		buttonGroupMethodAmortization.add(radioButtonProgressive);
		radioButtonProgressive.setMnemonic('P');
		radioButtonProgressive.setText("Progressive method");
		radioButtonProgressive
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						radioButtonProgressiveActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelMethodAmortization.add(radioButtonProgressive, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 1.0;
		add(panelMethodAmortization, gridBagConstraints);

		panelLoanData.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Loan data"));
		panelLoanData.setLayout(new java.awt.GridBagLayout());

		jLabel1.setText("Amount of money");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
		panelLoanData.add(jLabel1, gridBagConstraints);

		jLabel2.setText("Interest rate");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
		panelLoanData.add(jLabel2, gridBagConstraints);

		jLabel3.setText("Time to amortize");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
		panelLoanData.add(jLabel3, gridBagConstraints);

		jLabel4.setText("Payment period");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
		panelLoanData.add(jLabel4, gridBagConstraints);

		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelLoanData.add(jTextField1, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelLoanData.add(jTextField2, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelLoanData.add(jTextField3, gridBagConstraints);

		jComboBox2
				.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
						"MONTHLY", "TWICEMONTHLY", "QUARTERLY", "BIANNUAL",
						"ANNUAL" }));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelLoanData.add(jComboBox2, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 1.0;
		add(panelLoanData, gridBagConstraints);

		panelLoanOptions.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Options"));
		panelLoanOptions.setLayout(new java.awt.GridBagLayout());

		buttonGroupLoanOption.add(jRadioButtonAddToDebt);
		jRadioButtonAddToDebt.setText("add to debt");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelLoanOptions.add(jRadioButtonAddToDebt, gridBagConstraints);

		buttonGroupLoanOption.add(jRadioButtonPayAtCounter);
		jRadioButtonPayAtCounter.setText("pay at counter");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelLoanOptions.add(jRadioButtonPayAtCounter, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 1.0;
		add(panelLoanOptions, gridBagConstraints);

		panelCommissions.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Commissions"));
		panelCommissions.setLayout(new java.awt.GridBagLayout());

		jLabelCancelCommission.setText("Cancel commission");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
		panelCommissions.add(jLabelCancelCommission, gridBagConstraints);

		jLabelModifyCommission.setText("Modify commission");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
		panelCommissions.add(jLabelModifyCommission, gridBagConstraints);

		jLabelOpeningCommission.setText("Opening commission");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
		panelCommissions.add(jLabelOpeningCommission, gridBagConstraints);

		jLabelStudyCommission.setText("Study commission");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
		panelCommissions.add(jLabelStudyCommission, gridBagConstraints);

		jSliderCancelCommission
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						jSliderCancelCommissionStateChanged(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelCommissions.add(jSliderCancelCommission, gridBagConstraints);

		jSliderModifyCommission
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						jSliderModifyCommissionStateChanged(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelCommissions.add(jSliderModifyCommission, gridBagConstraints);

		jSliderOpeningCommission
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						jSliderOpeningCommissionStateChanged(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelCommissions.add(jSliderOpeningCommission, gridBagConstraints);

		jSliderStudyCommission
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						jSliderStudyCommissionStateChanged(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.8;
		gridBagConstraints.weighty = 1.0;
		panelCommissions.add(jSliderStudyCommission, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 1.0;
		add(panelCommissions, gridBagConstraints);
	}

	private void radioButtonFrenchActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void radioButtonProgressiveActionPerformed(
			java.awt.event.ActionEvent evt) {

	}

	private void jButtonCalculateActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Double money = Double.parseDouble(jTextField1.getText());
			Double interest = Double.parseDouble(jTextField2.getText());
			Integer time = Integer.parseInt(jTextField3.getText());
			this.model.setMoney(money);
			this.model.setInterest(interest);
			this.model.setTime(time);
		} catch (NumberFormatException numberFormatException) {

		}

		String paymentPeriodString = jComboBox2.getSelectedItem().toString();

		switch (paymentPeriodString) {
		case "MONTHLY":
			this.model.setPaymentPeriod(PaymentPeriod.MONTHLY);
			break;
		case "TWICEMONTHLY":
			this.model.setPaymentPeriod(PaymentPeriod.TWICEMONTHLY);
			break;
		case "QUARTERLY":
			this.model.setPaymentPeriod(PaymentPeriod.QUARTERLY);
			break;
		case "BIANNUAL":
			this.model.setPaymentPeriod(PaymentPeriod.BIANNUAL);
			break;
		case "ANNUAL":
			this.model.setPaymentPeriod(PaymentPeriod.ANNUAL);
			break;
		}

		if (this.buttonGroupMethodAmortization.getSelection() != null) {
			char typeMethod = (char) this.buttonGroupMethodAmortization
					.getSelection().getMnemonic();
			switch (typeMethod) {
			case 'A':
				try {
					this.model.createLoan(KindOfMethod.American);
				} catch (CommissionException e) {
					e.printStackTrace();
				}
				break;
			case 'F':
				try {
					this.model.createLoan(KindOfMethod.French);
				} catch (CommissionException e) {
					e.printStackTrace();
				}
				break;
			case 'I':
				try {
					this.model.createLoan(KindOfMethod.Italian);
				} catch (CommissionException e) {
					e.printStackTrace();
				}
				break;
			case 'P':
				try {
					this.model.createLoan(KindOfMethod.Progressive);
				} catch (CommissionException e) {
					e.printStackTrace();
				}
				break;
			case 'G':
				try {
					this.model.createLoan(KindOfMethod.German);
				} catch (CommissionException e) {
					e.printStackTrace();
				}
				break;
			default:
				try {
					this.model.createLoan(KindOfMethod.French);
				} catch (CommissionException e) {
					e.printStackTrace();
				}
				break;

			}

		}
		AssetsLoanPaymentsPanel panel = new AssetsLoanPaymentsPanel(this.frame);

		ArrayList<ScheduledPayment> payments = this.model.getPayments();

		for (ScheduledPayment payment : payments) {
			panel.addPayment(payment);
		}

		frame.changePanel(panel);

	}

	private void radioButtonItalianActionPerformed(
			java.awt.event.ActionEvent evt) {

	}

	private void radioButtonAmericanActionPerformed(
			java.awt.event.ActionEvent evt) {

	}

	private void radioButtonGermanActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jSliderCancelCommissionStateChanged(
			javax.swing.event.ChangeEvent evt) {
		this.jLabelCancelCommission.setText("Cancel commission "
				+ this.jSliderCancelCommission.getValue());
		double commission = this.jSliderCancelCommission.getValue();
		this.model.setCancelCommission(commission);
		try {
			this.model.setStrategyCancelCommission(new PercentCommission(
					commission));
		} catch (CommissionException e) {
			e.printStackTrace();
		}
	}

	private void jSliderModifyCommissionStateChanged(
			javax.swing.event.ChangeEvent evt) {
		this.jLabelModifyCommission.setText("Modify commission "
				+ this.jSliderModifyCommission.getValue());
		float commission = this.jSliderModifyCommission.getValue();
		this.model.setModifyCommission(commission);
		try {
			this.model.setStrategyModifyCommission(new PercentCommission(
					commission));
		} catch (CommissionException e) {
			e.printStackTrace();
		}
	}

	private void jSliderOpeningCommissionStateChanged(
			javax.swing.event.ChangeEvent evt) {
		this.jLabelOpeningCommission.setText("Opening commission "
				+ this.jSliderOpeningCommission.getValue());
		float commission = this.jSliderOpeningCommission.getValue();
		this.model.setOpenningCommission(commission);
		try {
			this.model.setStrategyOpenningCommission(new PercentCommission(
					commission));
		} catch (CommissionException e) {
			e.printStackTrace();
		}
	}

	private void jSliderStudyCommissionStateChanged(
			javax.swing.event.ChangeEvent evt) {
		this.jLabelStudyCommission.setText("Study commission "
				+ this.jSliderStudyCommission.getValue());
		float commission = this.jSliderStudyCommission.getValue();
		this.model.setStudyCommission(commission);
		try {
			this.model.setStrategyStudyCommission(new PercentCommission(
					commission));
		} catch (CommissionException e) {
			e.printStackTrace();
		}
	}

}
