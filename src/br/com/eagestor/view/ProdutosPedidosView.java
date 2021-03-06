/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.view;

import br.com.eagestor.dao.PedidosDAO;
import br.com.eagestor.dao.ProdutosDAO;
import br.com.eagestor.domain.Medidas;
import br.com.eagestor.domain.Pedidoprodutos;
import br.com.eagestor.domain.Produtos;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.com.eagestor.domain.PedidoprodutosPK;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Erick
 */
public class ProdutosPedidosView extends javax.swing.JDialog {

    /**
     * Creates new form ProdutosPedidos
     * @param parent
     * @param modal
     */
    public ProdutosPedidosView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Produtos pedidos");
        this.carregaProdutos();
        this.setAcessibilidade();
        this.carregaMedidas();
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
        jPanel9 = new javax.swing.JPanel();
        fecharBT = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        salvarBT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BuscaProdutoField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        produtoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        quantidadeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        medidaCB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel9.setBackground(new java.awt.Color(102, 102, 102));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecharBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/eagestor/icones/cancel.png"))); // NOI18N
        fecharBT.setText("FECHAR");
        fecharBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharBTActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        salvarBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/eagestor/icones/ok.png"))); // NOI18N
        salvarBT.setText("ADICIONAR");
        salvarBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salvarBT)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecharBT)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecharBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(salvarBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setText("Produto:");

        BuscaProdutoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscaProdutoFieldKeyReleased(evt);
            }
        });

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Quantidade", "Medida", "Valor"
            }
        ));
        masterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterTableMouseClicked(evt);
            }
        });
        masterTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                masterTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(masterTable);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes do produto a ser pedidos"));

        jLabel2.setText("Produto:");

        produtoField.setEditable(false);
        produtoField.setText("Nome do produto");

        jLabel3.setText("Quantidade:");

        quantidadeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantidadeFieldKeyReleased(evt);
            }
        });

        jLabel4.setText("Medida:");

        medidaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unidade", "Kilogramas", "Litros" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(produtoField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(medidaCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE)
                        .addComponent(quantidadeField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(produtoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quantidadeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(medidaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(BuscaProdutoField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuscaProdutoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        int row = masterTable.getSelectedRow();
        prod = listProd.get(row);
        produtoField.setText(prod.getNome());
        ppf = new PedidoprodutosPK();
        ppf.setIdProduto(prod.getIdProduto());
        ppf.setIdPedido(new PedidosDAO().getProximoID());
        
        medidaCB.setSelectedIndex(prod.getIdMedida().getIdMedida() -1);
    }//GEN-LAST:event_masterTableMouseClicked

    private void masterTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_masterTableKeyReleased
        int row = masterTable.getSelectedRow();
        prod = listProd.get(row);
        produtoField.setText(prod.getNome());
        ppf = new PedidoprodutosPK();
        ppf.setIdProduto(prod.getIdProduto());
        ppf.setIdPedido(new PedidosDAO().getProximoID());
    }//GEN-LAST:event_masterTableKeyReleased

    private void salvarBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBTActionPerformed
        pp = new Pedidoprodutos();
        pp.setQuantidade(Double.parseDouble(quantidadeField.getText()));
        pp.setPedidoprodutosPK(ppf);
        pp.setProdutos(prod);
        
        this.setPp(pp);
        this.dispose();
    }//GEN-LAST:event_salvarBTActionPerformed

    private void fecharBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharBTActionPerformed
        this.dispose();
    }//GEN-LAST:event_fecharBTActionPerformed

    private void BuscaProdutoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscaProdutoFieldKeyReleased
        String txt = BuscaProdutoField.getText().toUpperCase();
        BuscaProdutoField.setText(txt);
        listProd = new ProdutosDAO().listaProdutos(txt);
        this.organizaTabela();
    }//GEN-LAST:event_BuscaProdutoFieldKeyReleased

    private void quantidadeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadeFieldKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_ENTER)){
            try {
                double i = Double.parseDouble(quantidadeField.getText().replace(",", "."));
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Digite apenas numeros", "Cuidado",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_quantidadeFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProdutosPedidosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutosPedidosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutosPedidosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutosPedidosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ProdutosPedidosView dialog = new ProdutosPedidosView(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BuscaProdutoField;
    private javax.swing.JButton fecharBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable masterTable;
    private javax.swing.JComboBox<String> medidaCB;
    private javax.swing.JTextField produtoField;
    private javax.swing.JTextField quantidadeField;
    private javax.swing.JButton salvarBT;
    // End of variables declaration//GEN-END:variables
    private List<Produtos> listProd = new ArrayList<>();
    private List<Medidas> listMed = new ArrayList<>();
    private Produtos prod;
    private Pedidoprodutos pp;
    private PedidoprodutosPK ppf;
    
private void carregaMedidas(){
        listMed = new ProdutosDAO().listaMedidas();
        medidaCB.removeAllItems();
        for (Medidas med : listMed){
            medidaCB.addItem(med.getDescricao() + " - " + med.getUnidade());
        }
    }
    
    private void carregaProdutos(){
        listProd = new ProdutosDAO().listaProdutos();
        this.organizaTabela();
    }
    
    private void organizaTabela(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Quantidade");
        model.addColumn("Medida");
        model.addColumn("Valor Compra");
        
        for (int i = 0; i < listProd.size(); i++){
            model.addRow(new String[]{
                listProd.get(i).getNome(),
                listProd.get(i).getQuantidade().toString(),
                listProd.get(i).getIdMedida().getDescricao(),
                new DecimalFormat("¤#,##0.00").format(listProd.get(i).getValorCompra()),
            });
        }
        masterTable.setModel(model);
    }

    public Pedidoprodutos getPp() {
        return pp;
    }

    public void setPp(Pedidoprodutos pp) {
        this.pp = pp;
    }

    public String getNome(){
        return produtoField.getText();
    }

    public PedidoprodutosPK getPpf() {
        return ppf;
    }

    public void setPpf(PedidoprodutosPK ppf) {
        this.ppf = ppf;
    }

    public void setAcessibilidade() {
        JRootPane meurootpane = getRootPane();
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?");
                if (i == 0){
                    dispose();
                }
                
            }
        });
    }    
}
