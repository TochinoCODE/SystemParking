package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class principal extends javax.swing.JFrame {
    
    DefaultTableModel model=new DefaultTableModel();//LLAMAMOS A LA TABLA
    
    String barra=File.separator;//ayuda a mejorar la ruta de acceso a los archivos
    String ubicacion = System.getProperty("user.dir")+barra+"registros"+barra;//crea la ubicacion del registro en txt
    
    File contenedor=new File(ubicacion);
    File []registros=contenedor.listFiles();
    
    String []titulo={"N° DE PLACA","PROPIETARIO","VEHICULO","HORA DE INGRESO"};//CREA LOS TITULOS DE LA TABLA
    DefaultTableModel dtm=new DefaultTableModel(null,titulo);//agraga los titulos
    
    TableRowSorter trs;//propiedad para identificar por filas

    public principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        tabla();
    }

    private void crear(){//crea el metodo crear
        String archivo = txtplaca.getText()+".txt";//registra el texto de txtplaca en un archivo de texto
        File crea_ubicacion=new File(ubicacion);//crea la ubicacion de los archivos
        File crea_archivo=new File(ubicacion+archivo);
        if(txtplaca.getText().equals("")||txtpropietario.getText().equals("")||txtdni.getText().equals("")||txthora.getText().equals("")){//condicion de ingreso de los datos
            JOptionPane.showMessageDialog(rootPane,"Rellene los datos");//sino ingresa los datos muestra este mensaje
        }
        else{//si ingresa los datos
            try{
                if(crea_archivo.exists()){//condicion para verificar que el vehiculo no esta registrado
                JOptionPane.showMessageDialog(rootPane,"El vehiculo ya esta registrado");//si esta registrado muestra esto
                }
                else{//si no esta registrado
                    crea_ubicacion.mkdirs();
                    Formatter crea=new Formatter(ubicacion+archivo);//crea el archivo con los datos
                    crea.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s","PLACA="+txtplaca.getText(),"PROPIETARIO="+//captura los datos de los jtxt
                            txtpropietario.getText(),"DNI="+txtdni.getText(),"TIPOVEHICULO="+jbxvehiculo.getSelectedItem(),//captura los datos de los jtxt
                            "HORA="+txthora.getText(),"MIN="+txtmin.getText(),"TIEMPO="+txthora.getText()+":"+txtmin.getText());//captura los datos de los jtxt
                    crea.close();
                    JOptionPane.showMessageDialog(rootPane,"Vehiculo registrado");//muestra el mensaje de creacion
                    actualizartabla();//actualiza la tabla
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane,"No se pudo registrar");//si hay un error en crear el registro muestra este mensaje
            }
        }
    }
    
    private void retirar(){//crea el metodoretirar
        File url = new File(ubicacion+txtretirarplaca.getText()+".txt");//accede a la carpeta registros y busca por la placa
        String btns[]={"Retirar","Cancelar"};//crea botones
        if(txtretirarplaca.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Indique que vehiculo desea retirar");
        }
        else{
            if(url.exists()){
                try{
                    FileInputStream cerrar =new FileInputStream(url);//accedea a la url del archivo
                    cerrar.close();
                    System.gc();
                    int dec = JOptionPane.showOptionDialog(rootPane,"¿Estas seguro de retirar el vehiculo? "+txtretirarplaca.getText(),
                            "Retirar", 0, 0, null, btns, null);//PREGUNTA SI QUEREMOS ELIMINAR
                    if(dec==JOptionPane.YES_OPTION){//compara si la eleccion es si
                        url.delete();
                        JOptionPane.showMessageDialog(rootPane,"Vehiculo retirado");
                        actualizartabla();
                    }
                    if(dec==JOptionPane.NO_OPTION){//compara si la eleccion es  no
                    }
                }
                catch(Exception e){
                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane,"El vehiculo no existe");
            }
        }
    }
    
    private void tabla(){
        for(int i=0;i<registros.length;i++){
            File url=new File(ubicacion+registros[i].getName());//toma el archivo de los registros
            try{
                FileInputStream fis=new FileInputStream(url);//accdedemos a los registros
                Properties mostrar=new Properties(); //creamos la propieda mostrar
                mostrar.load(fis);//carga los datos
                String filas[]={mostrar.getProperty("PLACA",""),
                mostrar.getProperty("PROPIETARIO"),mostrar.getProperty("TIPOVEHICULO"),mostrar.getProperty("TIEMPO")};//agarra el numero de placa y agrega todo lo que contiene en la tabla
                dtm.addRow(filas);//muestra en la tabla todo lo que agregó
            }catch(Exception e){}
        }
        tabla.setModel(dtm);//llama a la tabla aplicando todo lo de antes configurado
    }
    
    private void actualizartabla(){
        registros = contenedor.listFiles();//actualiza los registros
        dtm.setRowCount(0);
        tabla();
    }
    
    private void precios(){
        DecimalFormat df=new DecimalFormat("#.00");//sirva par alimistar la cantidad de decimales
        double total,preciomin=0.10,totalpagar;
        int horaentrada,minutoentrada,horasalida,minutosalida,a,b;
        horaentrada=Integer.parseInt(txtmosthoraingreso.getText());
        minutoentrada=Integer.parseInt(txtmostminingreso.getText());
        horasalida=Integer.parseInt(txthsalida.getText());
        minutosalida=Integer.parseInt(txtmsalida.getText());
        if(horasalida<horaentrada){
            JOptionPane.showMessageDialog(rootPane,"Hora de salida incorrecta");
        }
        else{
            a=horaentrada*60;
            b=horasalida*60;
            total=(b+minutosalida)-(a+minutoentrada);
            totalpagar=total*preciomin;
            txtpagar.setText(String.valueOf(df.format(totalpagar)));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtplaca = new javax.swing.JTextField();
        txtpropietario = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        txtmin = new javax.swing.JTextField();
        jbxvehiculo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtretirarplaca = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtmsalida = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txthsalida = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtpagar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtmostplaca = new javax.swing.JTextField();
        txtmostprop = new javax.swing.JTextField();
        txtmostdni = new javax.swing.JTextField();
        txtsalida2 = new javax.swing.JTextField();
        txtmosthoraingreso = new javax.swing.JTextField();
        txtmostminingreso = new javax.swing.JTextField();
        txtsalida1 = new javax.swing.JTextField();
        txtmostvehi = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parking System");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("N° de placa:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Propietario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DNI:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hora de ingreso:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo de vehiculo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, -1));

        txtplaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtplaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtplacaKeyTyped(evt);
            }
        });
        jPanel1.add(txtplaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 80, -1));

        txtpropietario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtpropietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpropietarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtpropietario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 240, -1));

        txtdni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdniKeyTyped(evt);
            }
        });
        jPanel1.add(txtdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 80, -1));

        txthora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txthora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthoraKeyTyped(evt);
            }
        });
        jPanel1.add(txthora, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 30, -1));

        txtmin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtminKeyTyped(evt);
            }
        });
        jPanel1.add(txtmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 30, -1));

        jbxvehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auto", "Camioneta", "Motocicleta", "Otro" }));
        jbxvehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jbxvehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(":");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 10, 20));

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save pequeño.png"))); // NOI18N
        jButton1.setText("REGISTRAR");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 130, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/parking-icon-20.jpg"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 200, 150));

        jLabel8.setFont(new java.awt.Font("Gabriola", 1, 60)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Parking ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("Gabriola", 1, 60)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Centro");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 680, 10));

        jTabbedPane1.addTab("INGRESAR VEHICULO", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 204, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 26, 650, 380));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ingrese el numero de placa:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, 20));

        txtfiltro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        jPanel2.add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 460, 120, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("BUSCAR");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 40, 40));

        jTabbedPane1.addTab("LISTA DE VEHICULOS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 204, 51));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ingrese el numero de placa:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 170, 20));

        txtretirarplaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtretirarplaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtretirarplacaKeyTyped(evt);
            }
        });
        jPanel3.add(txtretirarplaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 80, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ingrese la hora de salida:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        txtmsalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmsalidaKeyTyped(evt);
            }
        });
        jPanel3.add(txtmsalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 30, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(":");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, 10, -1));

        txthsalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthsalidaKeyTyped(evt);
            }
        });
        jPanel3.add(txthsalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 30, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Hora");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Minuto");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cas.png"))); // NOI18N
        jButton2.setText("RETIRAR");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 110, 40));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total a pagar:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, -1, 20));

        txtpagar.setEditable(false);
        txtpagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtpagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 80, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Numero de placa:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Propietario:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("DNI:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Vehiculo:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Hora de ingreso:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Hora de salida:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, -1, -1));

        txtmostplaca.setEditable(false);
        jPanel3.add(txtmostplaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 90, -1));

        txtmostprop.setEditable(false);
        jPanel3.add(txtmostprop, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 180, -1));

        txtmostdni.setEditable(false);
        jPanel3.add(txtmostdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 90, -1));

        txtsalida2.setEditable(false);
        txtsalida2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtsalida2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 40, -1));

        txtmosthoraingreso.setEditable(false);
        txtmosthoraingreso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmosthoraingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmosthoraingresoActionPerformed(evt);
            }
        });
        jPanel3.add(txtmosthoraingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 40, -1));

        txtmostminingreso.setEditable(false);
        txtmostminingreso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtmostminingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 40, -1));

        txtsalida1.setEditable(false);
        txtsalida1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtsalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 40, -1));

        txtmostvehi.setEditable(false);
        jPanel3.add(txtmostvehi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 90, -1));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText(":");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 10, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText(":");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 10, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 680, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/parking-icon-20.jpg"))); // NOI18N
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 200, 150));

        jLabel27.setFont(new java.awt.Font("Gabriola", 1, 48)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Parking ");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, 40));

        jLabel28.setFont(new java.awt.Font("Gabriola", 1, 48)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Centro");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, -1, 40));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, 220));

        jTabbedPane1.addTab("RETIRAR VEHICULOS", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmosthoraingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmosthoraingresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmosthoraingresoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        File url=new File(ubicacion+txtretirarplaca.getText()+".txt");//accede a la ubicacion de los archivos
        if(txtretirarplaca.getText().equals("")||txthsalida.getText().equals("")||txtmsalida.getText().equals("")){//condicion
            JOptionPane.showMessageDialog(rootPane,"Rellene los campos");
        }
        else{
            if(url.exists()){//si la url del txtretirarplaca existe
                try{
                    FileInputStream fis=new FileInputStream(url);
                    Properties mostrar=new Properties();
                    mostrar.load(fis);
                    txtmostplaca.setText(mostrar.getProperty("PLACA"));//carga la placa ... igual pasa con los de abajo
                    txtmostprop.setText(mostrar.getProperty("PROPIETARIO"));
                    txtmostdni.setText(mostrar.getProperty("DNI"));
                    txtmostvehi.setText(mostrar.getProperty("TIPOVEHICULO"));
                    txtmosthoraingreso.setText(mostrar.getProperty("HORA"));
                    txtmostminingreso.setText(mostrar.getProperty("MIN"));
                    txtsalida1.setText(txthsalida.getText());
                    txtsalida2.setText(txtmsalida.getText());
                    precios();
                }catch(Exception e){}

            }
            retirar();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txthsalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthsalidaKeyTyped
        // TODO add your handling code here:
        if(txthsalida.getText().length()>=2){//limitamos el numero de caracteres a 2
            evt.consume();
        }
    }//GEN-LAST:event_txthsalidaKeyTyped

    private void txtmsalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmsalidaKeyTyped
        // TODO add your handling code here:
        if(txtmsalida.getText().length()>=2){//limitamos el numero de caracteres a 2
            evt.consume();
        }
    }//GEN-LAST:event_txtmsalidaKeyTyped

    private void txtretirarplacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtretirarplacaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtretirarplacaKeyTyped

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped

        txtfiltro.addKeyListener(new KeyAdapter(){//muestra el evento

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(txtfiltro.getText(),0));//busca por la palabra en txtfiltro
            }
        });
        trs=new TableRowSorter(dtm);
        tabla.setRowSorter(trs);

        if(txtfiltro.getText().length()>=6){//limitamos el numero de caracteres a 2
            evt.consume();
        }
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        crear();//llama al metodo crear
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtminKeyTyped

        if(txtmin.getText().length()>=2){//limitamos el numero de caracteres a 2
            evt.consume();
        }
    }//GEN-LAST:event_txtminKeyTyped

    private void txthoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthoraKeyTyped

        if(txthora.getText().length()>=2){//limitamos el numero de caracteres a 2
            evt.consume();
        }
    }//GEN-LAST:event_txthoraKeyTyped

    private void txtdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyTyped

        if(txtdni.getText().length()>=8){//limitamos el numero de caracteres a 8
            evt.consume();
        }
    }//GEN-LAST:event_txtdniKeyTyped

    private void txtpropietarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpropietarioKeyTyped

        if(txtpropietario.getText().length()>=35){//limitamos el numero de caracteres a 35
            evt.consume();
        }
    }//GEN-LAST:event_txtpropietarioKeyTyped

    private void txtplacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtplacaKeyTyped

        if(txtplaca.getText().length()>=6){//limitamos el numero de caracteres a 6
            evt.consume();
        }
    }//GEN-LAST:event_txtplacaKeyTyped

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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jbxvehiculo;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txthora;
    private javax.swing.JTextField txthsalida;
    private javax.swing.JTextField txtmin;
    private javax.swing.JTextField txtmostdni;
    private javax.swing.JTextField txtmosthoraingreso;
    private javax.swing.JTextField txtmostminingreso;
    private javax.swing.JTextField txtmostplaca;
    private javax.swing.JTextField txtmostprop;
    private javax.swing.JTextField txtmostvehi;
    private javax.swing.JTextField txtmsalida;
    private javax.swing.JTextField txtpagar;
    private javax.swing.JTextField txtplaca;
    private javax.swing.JTextField txtpropietario;
    private javax.swing.JTextField txtretirarplaca;
    private javax.swing.JTextField txtsalida1;
    private javax.swing.JTextField txtsalida2;
    // End of variables declaration//GEN-END:variables
}
