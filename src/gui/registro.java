package gui;

import java.io.File;
import java.util.Formatter;
import javax.swing.JOptionPane;

/**
 *
 * @author SKULLGAME
 */
public class registro extends javax.swing.JFrame {
    
    String barra=File.separator;//ayuda a mejorar la ruta de acceso a los archivos
    String ubicacion = System.getProperty("user.dir")+barra+"usuarios"+barra;//crea la ubicacion del registro en txt
    
    File contenedor=new File(ubicacion);
    File []usuarios=contenedor.listFiles();

    public registro() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void registrarse(){//crea el metodo crear
        String archivo = txtus.getText()+".txt";//registra el texto de los jtxtuser en un archivo de texto
        File crea_ubicacion=new File(ubicacion);//crea la ubicacion de los archivos
        File crea_archivo=new File(ubicacion+archivo);
        if(txtname.getText().equals("")||txtus.getText().equals("")||txtcon.getText().equals("")||txtap.getText().equals("")||txtdoc.getText().equals("")){//condicion de ingreso de los datos
            JOptionPane.showMessageDialog(rootPane,"Rellene sus datos");//sino ingresa los datos muestra este mensaje
        }
        else{//si ingresa los datos
            try{
                if(crea_archivo.exists()){//condicion para verificar que el empleado no esta registrado
                JOptionPane.showMessageDialog(rootPane,"El empleado ya esta registrado");//si esta registrado muestra esto
                }
                else{//si no esta registrado
                    crea_ubicacion.mkdirs();
                    Formatter crea=new Formatter(ubicacion+archivo);//crea el archivo con los datos
                    crea.format("%s\r\n%s\r\n%s\r\n%s\r\n%s","PASS="+txtcon.getText(),"USUARIO="+//captura los datos de los jtxt
                            txtus.getText(),"APELLIDOS="+txtap.getText(),"DOCUMENTO="+txtdoc.getText(),//captura los datos de los jtxt
                            "NOMBRE="+txtname.getText());//captura los datos de los jtxt
                    crea.close();
                    JOptionPane.showMessageDialog(rootPane,"Empleado registrado");//muestra el mensaje de creacion
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane,"No se pudo registrar");//si hay un error en crear el registro muestra este mensaje
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtname = new javax.swing.JTextField();
        txtap = new javax.swing.JTextField();
        txtdoc = new javax.swing.JTextField();
        txtus = new javax.swing.JTextField();
        txtcon = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRARSE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 110, 35));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DNI:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Usuario:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save pequeño.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 100, 40));
        jPanel1.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 170, -1));
        jPanel1.add(txtap, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 170, -1));
        jPanel1.add(txtdoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 100, -1));
        jPanel1.add(txtus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 100, -1));
        jPanel1.add(txtcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        registrarse();
        inicio inicio =new inicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtap;
    private javax.swing.JPasswordField txtcon;
    private javax.swing.JTextField txtdoc;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtus;
    // End of variables declaration//GEN-END:variables
}
