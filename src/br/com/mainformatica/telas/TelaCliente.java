/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mainformatica.telas;

import java.sql.*;
import br.com.mainformatica.dal.ModuloConexao;
import javax.swing.JOptionPane;
// A linha abaixo importa recursos da biblioteca para fazer a consulta rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Usuario
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
	initComponents();
	conexao = ModuloConexao.conector();
    }

    // metodo para adiocinar usuários
    private void adicionar() {
	String sql = "insert into tbclientes (nomecliente, endcliente, bairrocliente, cidadecliente, estadocliente, cepcliente, fonecliente, emailcliente) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtNome.getText());
	    pst.setString(2, txtEndereco.getText());
	    pst.setString(3, txtBairro.getText());
	    pst.setString(4, txtCidade.getText());
	    pst.setString(5, txtEstado.getText());
	    pst.setString(6, txtCep.getText());
	    pst.setString(7, txtFone.getText());
	    pst.setString(8, txtEmail.getText());
            //pst.setString(9, txtdatanascimento.getText());
	    
	    // Validação dos campos obrigatorios
	    if (txtNome.getText().isEmpty() || txtEndereco.getText().isEmpty() || txtBairro.getText().isEmpty() || txtEndereco.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty() || txtEstado.getText().isEmpty() || txtCep.getText().isEmpty() || txtFone.getText().isEmpty() || txtEmail.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
	    } else {

		// a linha abaixo atualiza a tabela ususario com os dados do formulario
		// a estrutura abaixo é usada para a confirmação de dados na tabela
		int adicionado = pst.executeUpdate();
		if (adicionado > 0) {
		    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
		    txtNome.setText(null);
		    txtEndereco.setText(null);
		    txtBairro.setText(null);
		    txtCidade.setText(null);
		    txtEstado.setText(null);
		    txtCep.setText(null);
		    txtFone.setText(null);
		    txtEmail.setText(null);
                   // txtdatanascimento.setText(null);
		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    // Metodo para pesquisar clientes pelo nome com filtro
    private void pesquisar_cliente() {
	String sql = "Select * from tbclientes where nomecliente like ?";
	try {
	    pst = conexao.prepareStatement(sql);
	    //passando o conteudo da caixa de pesquisa para o interroga
	    //Atenção ao "%" continuação da String sql
	    pst.setString(1,txtProcurar.getText() + "%");
	    rs = pst.executeQuery();
	    // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
	    tblNome.setModel(DbUtils.resultSetToTableModel(rs));
	    
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }
       
    //Metodo para setar os campos do formulario com o conteudo da tabela
    public void setar_campos(){
     int setar = tblNome.getSelectedRow();
     txtCodigo.setText(tblNome.getModel().getValueAt(setar, 0).toString());
     txtNome.setText(tblNome.getModel().getValueAt(setar, 1).toString());
     txtEndereco.setText(tblNome.getModel().getValueAt(setar, 2).toString());
     txtBairro.setText(tblNome.getModel().getValueAt(setar, 3).toString());
     txtCidade.setText(tblNome.getModel().getValueAt(setar, 4).toString());
     txtEstado.setText(tblNome.getModel().getValueAt(setar, 5).toString());
     txtCep.setText(tblNome.getModel().getValueAt(setar, 6).toString());
     txtFone.setText(tblNome.getModel().getValueAt(setar, 7).toString());
     txtEmail.setText(tblNome.getModel().getValueAt(setar, 8).toString());
     //txtdatanascimento.setText(tblNome.getModel().getValueAt(setar, 9).toString());
      
     // A linha abaixo desabilita o botão adicionar
     btnAdicionar.setEnabled(false);
    }
    
     private void salvar () {
        String sql = "update tbclientes set nomecliente = ?, endcliente = ?, bairrocliente = ?, cidadecliente =?, estadocliente =?, cepcliente =?, fonecliente =?, emailcliente =?, where idcliente =? ";
        try {
      
            pst = conexao.prepareStatement(sql);
            
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtEndereco.getText());
            pst.setString(4, txtBairro.getText()); 
            pst.setString(5, txtCidade.getText());
            pst.setString(6, txtEstado.getText());
	    pst.setString(7, txtCep.getText());
	    pst.setString(8, txtFone.getText());
	    //pst.setString(9, txtEmail.getText());
	    pst.setInt(1, (int) tblNome.getModel().getValueAt(tblNome.getSelectedRow(), 0));
            	   	    
            if ((txtNome.getText().isEmpty()) || (txtEndereco.getText().isEmpty()) || (txtBairro.getText().isEmpty()) || (txtCidade.getText().isEmpty()) || (txtEstado.getText().isEmpty()) || (txtCep.getText().isEmpty()) || (txtFone.getText().isEmpty()) || (txtEmail.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else { 

                // a linha abaixo atualiza a tabela ususario com os dados do formulario
                // a estrutura abaixo é usada para a confirmação de dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterado com sucesso");
                    txtNome.setText(null);
                    txtEndereco.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtEstado.setText(null);
		    txtCep.setText(null);
                    txtFone.setText(null);
                    txtEmail.setText(null);
                    txtCodigo.setText(null);
		                       
		   // A linha abaixo habilita o botão adicionar
		    btnAdicionar.setEnabled(true);
                }
            }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void remover (){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este Cliente", "Atenção", JOptionPane.YES_NO_OPTION );
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where nomecliente = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtNome.getText()) ;
                int apagado = pst.executeUpdate ();
                if (apagado>0){
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                    txtNome.setText(null);
                    txtEndereco.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
		    txtEstado.setText(null);
		    txtCep.setText(null);
		    txtFone.setText(null);
		    txtEmail.setText(null);
                    //txtdatanascimento.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtProcurar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNome = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();
        txtCep = new javax.swing.JTextField();
        txtFone = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 19))); // NOI18N

        txtProcurar.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        txtProcurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcurarKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mainformatica/telas/Procurar.png"))); // NOI18N
        jLabel5.setText("hghg");

        tblNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblNome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Endereço"
            }
        ));
        tblNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNomeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNome);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 19))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel3.setText("End.:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel4.setText("Bairro:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel6.setText("Cidade:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel7.setText("Estado:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel8.setText("CEP:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel13.setText("Fone:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel14.setText("Email:");

        txtNome.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        txtEndereco.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        txtBairro.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        txtCidade.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        txtEstado.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoKeyTyped(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel1.setText("Código:");

        txtCodigo.setEnabled(false);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        txtCep.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        txtFone.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(273, 273, 273)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel7))))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mainformatica/telas/Adicionar Usuario.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mainformatica/telas/update.png"))); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mainformatica/telas/deletar-usuario.png"))); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionar)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(411, 411, 411))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar)
                    .addComponent(btnAdicionar)
                    .addComponent(btnExcluir))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
	// Metodo para add Clientes
	adicionar();

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtProcurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcurarKeyReleased
        // O evento abaixo é do tipo enquanto for digitando em tempo real vai pesquisar cliente
	pesquisar_cliente();
    }//GEN-LAST:event_txtProcurarKeyReleased

    private void tblNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNomeMouseClicked
        // Evento que será usado para setar os campos da tabela clicando com o mouse
	setar_campos();
    }//GEN-LAST:event_tblNomeMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salva os dados do cliente no banco de dados
	salvar ();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyTyped
        // TODO add your handling code here:
	int numero=2;
	if(txtEstado.getText().length()>=numero){
          evt.consume();
          JOptionPane.showMessageDialog(rootPane, "Somente 2 Caractere");
        }
    }//GEN-LAST:event_txtEstadoKeyTyped

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Remove o cliente
	remover ();
    }//GEN-LAST:event_btnExcluirActionPerformed

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNome;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtProcurar;
    // End of variables declaration//GEN-END:variables
}
