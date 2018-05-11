/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto9_SignUp;

import static Proyecto9_SignUp.Conexion.getConection;
import java.util.Date;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import rojerusan.RSPanelsSlider;

/**
 *
 * @author hcholes
 */
public class Principal extends javax.swing.JFrame {

    // DefaultTableModel modeloTabla;
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        jBReporte.setBorder(null);
    }

    public void mostrar() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Tipo Id");
        modelo.addColumn("Identificación");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Grado");
        modelo.addColumn("Email");
        modelo.addColumn("Celular");
        modelo.addColumn("fecha_registro");
        jTable1.setModel(modelo);
        String[] Datos = new String[8];
        // modelo.setColumnIdentifiers(new Object[]{"IDENTIFICACION", "NOMBRES", "APELLIDOS"});
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("select * from usuario");
            res = ps.executeQuery();
            int a = 0;
            while (res.next()) {
                Datos[0] = res.getString("tipodoc");
                Datos[1] = res.getString("doc");
                Datos[2] = res.getString("nombres");
                Datos[3] = res.getString("Apellidos");
                Datos[4] = res.getString("grado");
                Datos[5] = res.getString("email");
                Datos[6] = res.getString("celular");
                Datos[7] = res.getString("fecha_registro");
                modelo.addRow(Datos);

                a = a + 1;
                String texto1;
                texto1 = String.valueOf(a);
                cant_usuarios.setText(texto1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        limpiarcasillas();
    }

    public void mostrarlibros() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Autor");
        modelo.addColumn("Editorial");
        modelo.addColumn("Tomo");
        modelo.addColumn("Año");
        jTable2.setModel(modelo);
        String[] Datos = new String[6];
        // modelo.setColumnIdentifiers(new Object[]{"IDENTIFICACION", "NOMBRES", "APELLIDOS"});
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("select * from librosycatalogos2");
            res = ps.executeQuery();
            int a = 0;
            while (res.next()) {
                Datos[0] = res.getString("codigo");
                Datos[1] = res.getString("nombre");
                Datos[2] = res.getString("cod_autor");
                Datos[3] = res.getString("cod_editorial");
                Datos[4] = res.getString("tomo");
                Datos[5] = res.getString("año");
                modelo.addRow(Datos);

                a = a + 1;
                String texto1;
                texto1 = String.valueOf(a);
                cant_libros.setText(texto1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        limpiarcasillas2();
    }

    public void mostrarautores() {
        DefaultTableModel modelo1 = new DefaultTableModel();
        modelo1.addColumn("Codigo");
        modelo1.addColumn("Nombre");
        tabla_autores.setModel(modelo1);
        String[] Datos1 = new String[2];
        // modelo.setColumnIdentifiers(new Object[]{"IDENTIFICACION", "NOMBRES", "APELLIDOS"});
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("select * from autores2");
            res = ps.executeQuery();
            int a = 0;
            while (res.next()) {
                Datos1[0] = res.getString("cod_autor");
                Datos1[1] = res.getString("nom_autor");
                 modelo1.addRow(Datos1);

                a = a + 1;
                String texto1;
                texto1 = String.valueOf(a);
                cant_autores.setText(texto1);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
//        limpiarcasillas2();
    }

    public void mostrareditoriales() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha Registro");
        tabla_editorial.setModel(modelo);
        String[] Datos = new String[3];
        // modelo.setColumnIdentifiers(new Object[]{"IDENTIFICACION", "NOMBRES", "APELLIDOS"});
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("select * from editorial2");
            res = ps.executeQuery();
            int a = 0;
            while (res.next()) {
                Datos[0] = res.getString("Cod_editorial");
                Datos[1] = res.getString("nom_editorial");
                Datos[2] = res.getString("fecha_registro");
                modelo.addRow(Datos);

                a = a + 1;
                String texto1;
                texto1 = String.valueOf(a);
                cant_editorial.setText(texto1);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        limpiareditorial();
    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(ahora);
    }

    public void limpiarcasillas() {
        tipoId.setSelectedIndex(0);
        numId.setText(null);
        nombres.setText(null);
        apellidos.setText(null);
        tipoUsuario.setSelectedIndex(0);
        grado.setSelectedIndex(0);
        sexo.setSelectedIndex(0);
        ano.setSelectedIndex(0);
        telefono.setText(null);
        celular.setText(null);
        email.setText(null);
        fecha_registro.setText(getFechaActual());
        String ruta = "D:\\eclipse-workspace\\Proyecto9_SignUp\\src\\imagenes\\estudiante.png";
        rutaImagen.setText(null);
        Image foto = getToolkit().getImage(ruta);
        foto = foto.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        lblFoto.setIcon(new ImageIcon(foto));

    }

    public void limpiareditorial() {
        nomb_editorial.setText(null);
        codi_editorial.setText(null);
        fecha_registro2.setText(getFechaActual());

    }

    public void limpiarcasillas2() {

        codigolibro.setText(null);
        nombre_libro.setText(null);
        area.setText(null);
        tipoUsuario.setSelectedIndex(0);
        codAutor.setText(null);
        cod_editorial.setText(null);
        ano1.setSelectedIndex(0);
        tomo.setSelectedIndex(0);
        observacion.setText(null);
        fecha_registro1.setText(getFechaActual());
        String ruta1 = "D:\\eclipse-workspace\\Proyecto9_SignUp\\src\\imagenes\\libro.png";
        rutaImagen1.setText(null);
        Image foto = getToolkit().getImage(ruta1);
        foto = foto.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        lblFoto1.setIcon(new ImageIcon(foto));

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
        sidepane = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Configuracion = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Inicio = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Registro = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        LibrosyCatalogos = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Dispositivos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        pnl1 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jBReporte = new javax.swing.JButton();
        jBPrestamo = new javax.swing.JButton();
        jBEstadistica = new javax.swing.JButton();
        jBPrestamo1 = new javax.swing.JButton();
        pnl2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tipoId = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        numId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        nombres = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        grado = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        ano = new javax.swing.JComboBox<>();
        telefono = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        celular = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Guardar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        tipoUsuario = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fecha_registro = new javax.swing.JTextField();
        Limpiar = new javax.swing.JButton();
        sexo = new javax.swing.JComboBox<>();
        sexo1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        rutaImagen = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cant_usuarios = new javax.swing.JLabel();
        ReportarPDF = new javax.swing.JButton();
        pnl3 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        lblFoto1 = new javax.swing.JLabel();
        codigolibro = new javax.swing.JTextField();
        nombre_libro = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        area = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ano1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        fecha_registro1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        rutaImagen1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        cant_libros = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        Limpiar1 = new javax.swing.JButton();
        Modificar1 = new javax.swing.JButton();
        Eliminar1 = new javax.swing.JButton();
        Guardar1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        codAutor = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        cod_editorial = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        tomo = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        jLabel40 = new javax.swing.JLabel();
        pnl4 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        pnl5 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        pnl6 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        Regresar1 = new javax.swing.JButton();
        pnl7 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        Regrear3 = new javax.swing.JButton();
        pnl8 = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        Regresar2 = new javax.swing.JButton();
        pnl9 = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        Regresar3 = new javax.swing.JButton();
        autores = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        titulo_listaautores = new javax.swing.JLabel();
        Regresar4 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_autores = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        cant_autores = new javax.swing.JLabel();
        editorial = new javax.swing.JPanel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        Regresar5 = new javax.swing.JButton();
        NOMBREEDITO = new javax.swing.JLabel();
        nomb_editorial = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_editorial = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        cant_editorial = new javax.swing.JLabel();
        codi_editorial = new javax.swing.JTextField();
        NOMBREEDITO1 = new javax.swing.JLabel();
        Limpiar2 = new javax.swing.JButton();
        Modificar2 = new javax.swing.JButton();
        Eliminar2 = new javax.swing.JButton();
        Guardar2 = new javax.swing.JButton();
        NOMBREEDITO2 = new javax.swing.JLabel();
        fecha_registro2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepane.setBackground(new java.awt.Color(102, 0, 153));
        sidepane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 0, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/data-configuration.png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, -1));

        Configuracion.setBackground(new java.awt.Color(153, 0, 255));
        Configuracion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Configuracion.setForeground(new java.awt.Color(255, 255, 255));
        Configuracion.setText("Configuracion");
        Configuracion.setToolTipText("");
        Configuracion.setBorder(null);
        Configuracion.setContentAreaFilled(false);
        Configuracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfiguracionActionPerformed(evt);
            }
        });
        jPanel4.add(Configuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 120, 20));

        sidepane.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 340, 50));

        jPanel5.setBackground(new java.awt.Color(153, 0, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/home.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Inicio.setBackground(new java.awt.Color(153, 0, 255));
        Inicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Inicio.setForeground(new java.awt.Color(255, 255, 255));
        Inicio.setText("Inicio");
        Inicio.setToolTipText("");
        Inicio.setBorder(null);
        Inicio.setContentAreaFilled(false);
        Inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioActionPerformed(evt);
            }
        });
        jPanel5.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 40, 20));

        sidepane.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 340, 50));

        jPanel6.setBackground(new java.awt.Color(102, 0, 153));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro.png"))); // NOI18N
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Registro.setBackground(new java.awt.Color(153, 0, 255));
        Registro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Registro.setForeground(new java.awt.Color(255, 255, 255));
        Registro.setText("Registro de Usuarios");
        Registro.setToolTipText("");
        Registro.setBorder(null);
        Registro.setContentAreaFilled(false);
        Registro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });
        jPanel6.add(Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 150, 20));

        sidepane.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 340, 50));

        jPanel7.setBackground(new java.awt.Color(102, 0, 153));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/libros.png"))); // NOI18N
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        LibrosyCatalogos.setBackground(new java.awt.Color(153, 0, 255));
        LibrosyCatalogos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LibrosyCatalogos.setForeground(new java.awt.Color(255, 255, 255));
        LibrosyCatalogos.setText("Libros y Catalogos");
        LibrosyCatalogos.setToolTipText("");
        LibrosyCatalogos.setBorder(null);
        LibrosyCatalogos.setContentAreaFilled(false);
        LibrosyCatalogos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LibrosyCatalogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LibrosyCatalogosActionPerformed(evt);
            }
        });
        jPanel7.add(LibrosyCatalogos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 120, 20));

        sidepane.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 340, 50));

        jPanel8.setBackground(new java.awt.Color(102, 0, 153));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dispo.png"))); // NOI18N
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Dispositivos.setBackground(new java.awt.Color(153, 0, 255));
        Dispositivos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Dispositivos.setForeground(new java.awt.Color(255, 255, 255));
        Dispositivos.setText("Dispositivos");
        Dispositivos.setToolTipText("");
        Dispositivos.setBorder(null);
        Dispositivos.setContentAreaFilled(false);
        Dispositivos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dispositivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DispositivosActionPerformed(evt);
            }
        });
        jPanel8.add(Dispositivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 120, 20));

        sidepane.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 340, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/titulo.png"))); // NOI18N
        sidepane.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 80));
        sidepane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 230, 20));

        jPanel1.add(sidepane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 280, 600));

        jPanel9.setBackground(new java.awt.Color(204, 51, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("X");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1014, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 50));

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setName("pnl1"); // NOI18N
        pnl1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 630, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/titulos de menus.png"))); // NOI18N
        pnl1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 45, 250, 60));

        jBReporte.setBackground(new java.awt.Color(255, 255, 255));
        jBReporte.setForeground(new java.awt.Color(255, 255, 255));
        jBReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes.png"))); // NOI18N
        jBReporte.setBorder(null);
        jBReporte.setBorderPainted(false);
        jBReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBReporteActionPerformed(evt);
            }
        });
        pnl1.add(jBReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, -1, -1));

        jBPrestamo.setBackground(new java.awt.Color(255, 255, 255));
        jBPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        jBPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamos.png"))); // NOI18N
        jBPrestamo.setBorder(null);
        jBPrestamo.setBorderPainted(false);
        jBPrestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPrestamoActionPerformed(evt);
            }
        });
        pnl1.add(jBPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        jBEstadistica.setBackground(new java.awt.Color(255, 255, 255));
        jBEstadistica.setForeground(new java.awt.Color(255, 255, 255));
        jBEstadistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/estadistica.png"))); // NOI18N
        jBEstadistica.setBorder(null);
        jBEstadistica.setBorderPainted(false);
        jBEstadistica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBEstadistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEstadisticaActionPerformed(evt);
            }
        });
        pnl1.add(jBEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, -1, -1));

        jBPrestamo1.setBackground(new java.awt.Color(255, 255, 255));
        jBPrestamo1.setForeground(new java.awt.Color(255, 255, 255));
        jBPrestamo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/devolucion.png"))); // NOI18N
        jBPrestamo1.setBorder(null);
        jBPrestamo1.setBorderPainted(false);
        jBPrestamo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBPrestamo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPrestamo1ActionPerformed(evt);
            }
        });
        pnl1.add(jBPrestamo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        rSPanelsSlider1.add(pnl1, "card2");

        pnl2.setBackground(new java.awt.Color(255, 255, 255));
        pnl2.setName("pnl2"); // NOI18N
        pnl2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 630, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro1.png"))); // NOI18N
        pnl2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, -4, 340, 50));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Tipo de Documento:");
        pnl2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        tipoId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tipoId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "TI", "CC", "CE", "" }));
        pnl2.add(tipoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 100, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Numero de Documento:");
        pnl2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 83, -1, -1));
        pnl2.add(numId, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 83, 144, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nombres:");
        pnl2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 106, -1, -1));
        pnl2.add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 106, 144, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Apellidos:");
        pnl2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, -1));
        pnl2.add(apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 144, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Tipo Usuario:");
        pnl2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 156, -1, -1));

        grado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        grado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionne", "1A", "1B", "1C", "2A", "2B", "2C", "3A", "3B", "3C", "4A", "4B", "4C", "5A", "5B", "5C", "6A", "6B", "6C", "7A", "7B", "7C", "8A", "8B", "8C", "9A", "9B", "9C", "10A", "10B", "10C", "11A", "11B", "11C" }));
        pnl2.add(grado, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 178, 110, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Año:");
        pnl2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 206, 30, -1));

        ano.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ano.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "2018", "2019", "2020" }));
        pnl2.add(ano, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 203, 110, -1));
        pnl2.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 144, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Telefono:");
        pnl2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 60, -1));
        pnl2.add(celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 254, 144, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Celular:");
        pnl2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 255, -1, -1));
        pnl2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 260, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Ruta:");
        pnl2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Identifiacion", "Nombres", "Apellidos", "Grado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        pnl2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 780, 170));

        Guardar.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        pnl2.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 120, 40));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pnl2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 30, -1));

        Eliminar.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        pnl2.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, 120, 40));

        Modificar.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        pnl2.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 130, 40));

        tipoUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionne", "Estudiante", "Profesor", "Administrativo", "Particular" }));
        pnl2.add(tipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 153, 140, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Grado:");
        pnl2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 180, 40, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Email:");
        pnl2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, -1, -1));

        fecha_registro.setEnabled(false);
        pnl2.add(fecha_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 306, 140, -1));

        Limpiar.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        pnl2.add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 550, 120, 40));

        sexo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionne", "M", "F" }));
        pnl2.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 178, -1, -1));

        sexo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sexo1.setText("Sexo:");
        pnl2.add(sexo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, 40, -1));

        jButton2.setText("Seleccione");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pnl2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 335, -1, 20));

        rutaImagen.setEditable(false);
        rutaImagen.setText("D:\\eclipse-workspace\\Proyecto9_SignUp\\src\\imagenes");
        pnl2.add(rutaImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 335, 560, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Fecha Registro:");
        pnl2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 308, -1, -1));

        lblFoto.setBackground(new java.awt.Color(204, 204, 204));
        lblFoto.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblFoto.setForeground(new java.awt.Color(153, 0, 153));
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/estudiante.png"))); // NOI18N
        lblFoto.setToolTipText("");
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl2.add(lblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 300, 240));

        jLabel29.setText("Cantidad");
        pnl2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 50, -1));

        cant_usuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant_usuarios.setText("0");
        pnl2.add(cant_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 70, -1));

        ReportarPDF.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        ReportarPDF.setText("IMPRIMIR");
        ReportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportarPDFActionPerformed(evt);
            }
        });
        pnl2.add(ReportarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, 160, 40));

        rSPanelsSlider1.add(pnl2, "card3");

        pnl3.setBackground(new java.awt.Color(255, 255, 255));
        pnl3.setName("pnl3"); // NOI18N
        pnl3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 630, 19));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/libros1.png"))); // NOI18N
        pnl3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 58));

        lblFoto1.setBackground(new java.awt.Color(204, 204, 204));
        lblFoto1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblFoto1.setForeground(new java.awt.Color(153, 0, 153));
        lblFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/libro.png"))); // NOI18N
        lblFoto1.setToolTipText("");
        lblFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl3.add(lblFoto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 320, 290));
        pnl3.add(codigolibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 83, 144, -1));
        pnl3.add(nombre_libro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 106, 144, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Codigo:");
        pnl3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 85, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Nombre Libro o Catalogo:");
        pnl3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 108, -1, -1));

        jButton4.setText("...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        pnl3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 30, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Area:");
        pnl3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, -1));
        pnl3.add(area, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 144, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Año:");
        pnl3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 30, -1));

        ano1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ano1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "2018", "2019", "2020" }));
        pnl3.add(ano1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 110, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Ruta:");
        pnl3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, -1));

        fecha_registro1.setEnabled(false);
        pnl3.add(fecha_registro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 352, 140, -1));

        jTable2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Identifiacion", "Nombres", "Apellidos", "Grado"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        pnl3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 780, 130));

        rutaImagen1.setEditable(false);
        pnl3.add(rutaImagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 375, 560, -1));

        jButton5.setText("Seleccione");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        pnl3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 375, -1, 20));

        cant_libros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant_libros.setText("0");
        pnl3.add(cant_libros, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 70, -1));

        jLabel35.setText("Cantidad");
        pnl3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 50, -1));

        Limpiar1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Limpiar1.setText("Limpiar");
        Limpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar1ActionPerformed(evt);
            }
        });
        pnl3.add(Limpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 120, 40));

        Modificar1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Modificar1.setText("Modificar");
        Modificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar1ActionPerformed(evt);
            }
        });
        pnl3.add(Modificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 130, 40));

        Eliminar1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Eliminar1.setText("Eliminar");
        Eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar1ActionPerformed(evt);
            }
        });
        pnl3.add(Eliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 120, 40));

        Guardar1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Guardar1.setText("Guardar");
        Guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar1ActionPerformed(evt);
            }
        });
        pnl3.add(Guardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 120, 40));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Codigo Autor:");
        pnl3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));
        pnl3.add(codAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 144, -1));

        jButton6.setText("...");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnl3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 30, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Codigo Editorial:");
        pnl3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, -1, -1));
        pnl3.add(cod_editorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 144, -1));

        jButton7.setText("...");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        pnl3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 30, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Tomo:");
        pnl3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, -1, -1));

        tomo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tomo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" }));
        pnl3.add(tomo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 110, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText("Fecha Registro:");
        pnl3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 353, -1, -1));

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane3.setViewportView(observacion);

        pnl3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 280, 70));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText("Observacion:");
        pnl3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        rSPanelsSlider1.add(pnl3, "card4");

        pnl4.setBackground(new java.awt.Color(255, 255, 255));
        pnl4.setName("pnl4"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dispo1.png"))); // NOI18N

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl4Layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18))
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(470, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(pnl4, "card5");

        pnl5.setBackground(new java.awt.Color(255, 255, 255));
        pnl5.setName("pnl5"); // NOI18N
        pnl5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 630, 19));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuracion.png"))); // NOI18N
        pnl5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 43, -1, 62));

        rSPanelsSlider1.add(pnl5, "card6");

        pnl6.setBackground(new java.awt.Color(255, 255, 255));
        pnl6.setName("pnl6"); // NOI18N
        pnl6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl6.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 630, 19));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamos1.png"))); // NOI18N
        pnl6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 370, 50));

        Regresar1.setText("Regresar");
        Regresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regresar1ActionPerformed(evt);
            }
        });
        pnl6.add(Regresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, -1, -1));

        rSPanelsSlider1.add(pnl6, "card6");

        pnl7.setBackground(new java.awt.Color(255, 255, 255));
        pnl7.setName("pnl7"); // NOI18N
        pnl7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl7.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 74, 630, 19));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/estadistica1.png"))); // NOI18N
        pnl7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 400, 60));

        Regrear3.setText("Regresar");
        Regrear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regrear3ActionPerformed(evt);
            }
        });
        pnl7.add(Regrear3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 530, -1, -1));

        rSPanelsSlider1.add(pnl7, "card6");

        pnl8.setBackground(new java.awt.Color(255, 255, 255));
        pnl8.setName("pnl8"); // NOI18N
        pnl8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl8.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 630, 19));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes1.png"))); // NOI18N
        pnl8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 370, 60));

        Regresar2.setText("Regresar");
        Regresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regresar2ActionPerformed(evt);
            }
        });
        pnl8.add(Regresar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 550, -1, -1));

        rSPanelsSlider1.add(pnl8, "card6");

        pnl9.setBackground(new java.awt.Color(255, 255, 255));
        pnl9.setName("pnl8"); // NOI18N
        pnl9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl9.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 630, 19));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dev.png"))); // NOI18N
        pnl9.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 370, 60));

        Regresar3.setText("Regresar");
        Regresar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regresar3ActionPerformed(evt);
            }
        });
        pnl9.add(Regresar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 550, -1, -1));

        rSPanelsSlider1.add(pnl9, "card6");

        autores.setBackground(new java.awt.Color(255, 255, 255));
        autores.setName("pnl8"); // NOI18N
        autores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        autores.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 630, 19));

        titulo_listaautores.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titulo_listaautores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listaautores.png"))); // NOI18N
        autores.add(titulo_listaautores, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 570, 60));

        Regresar4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Regresar4.setText("Enviar");
        Regresar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regresar4ActionPerformed(evt);
            }
        });
        autores.add(Regresar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText("Buscar Nombre del Autor:");
        autores.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        autores.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 250, -1));

        tabla_autores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Codigo", "Nombre Autor"
            }
        ));
        jScrollPane4.setViewportView(tabla_autores);

        autores.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 720, 200));

        jLabel43.setText("Cantidad");
        autores.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, 50, -1));

        cant_autores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant_autores.setText("0");
        autores.add(cant_autores, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 70, -1));

        rSPanelsSlider1.add(autores, "card6");

        editorial.setBackground(new java.awt.Color(255, 255, 255));
        editorial.setName("pnl8"); // NOI18N
        editorial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        editorial.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 630, 19));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listaeditoriales.png"))); // NOI18N
        editorial.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 690, 60));

        Regresar5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Regresar5.setText("Enviar");
        Regresar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regresar5ActionPerformed(evt);
            }
        });
        editorial.add(Regresar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, 130, 40));

        NOMBREEDITO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NOMBREEDITO.setText("Fecha Registro:");
        editorial.add(NOMBREEDITO, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        nomb_editorial.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editorial.add(nomb_editorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 250, -1));

        tabla_editorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre Editorial", "Fecha Registro"
            }
        ));
        jScrollPane5.setViewportView(tabla_editorial);

        editorial.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 710, 190));

        jLabel46.setText("Cantidad");
        editorial.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 50, -1));

        cant_editorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant_editorial.setText("0");
        editorial.add(cant_editorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, 70, -1));

        codi_editorial.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editorial.add(codi_editorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 250, -1));

        NOMBREEDITO1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NOMBREEDITO1.setText("Codigo:");
        editorial.add(NOMBREEDITO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        Limpiar2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Limpiar2.setText("Limpiar");
        Limpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar2ActionPerformed(evt);
            }
        });
        editorial.add(Limpiar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 120, 40));

        Modificar2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Modificar2.setText("Modificar");
        Modificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar2ActionPerformed(evt);
            }
        });
        editorial.add(Modificar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, 130, 40));

        Eliminar2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Eliminar2.setText("Eliminar");
        Eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar2ActionPerformed(evt);
            }
        });
        editorial.add(Eliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 120, 40));

        Guardar2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        Guardar2.setText("Guardar");
        Guardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar2ActionPerformed(evt);
            }
        });
        editorial.add(Guardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 120, 40));

        NOMBREEDITO2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NOMBREEDITO2.setText("Nombre:");
        editorial.add(NOMBREEDITO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        fecha_registro2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fecha_registro2.setEnabled(false);
        editorial.add(fecha_registro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 250, -1));

        rSPanelsSlider1.add(editorial, "card6");

        jPanel1.add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 820, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int posicion = sidepane.getX();

        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -280, 2, 2, sidepane);

            //rSPanelsSlider1.setSize(1100, 610);
        } else {
            Animacion.Animacion.mover_derecha(-280, 0, 2, 2, sidepane);

            //rSPanelsSlider1.setSize(820, 610);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioActionPerformed
        if (!this.Inicio.isSelected()) {
            this.Inicio.setSelected(true);
            this.Registro.setSelected(false);
            this.LibrosyCatalogos.setSelected(false);
            this.Dispositivos.setSelected(false);
            this.Configuracion.setSelected(false);

            rSPanelsSlider1.setPanelSlider(20, pnl1, RSPanelsSlider.DIRECT.RIGHT);

            jPanel5.setBackground(new Color(153, 0, 255));
            jPanel6.setBackground(new Color(102, 0, 153));
            jPanel7.setBackground(new Color(102, 0, 153));
            jPanel8.setBackground(new Color(102, 0, 153));
            jPanel4.setBackground(new Color(102, 0, 153));

        }
    }//GEN-LAST:event_InicioActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        if (!this.Registro.isSelected()) {
            this.Inicio.setSelected(false);
            this.Registro.setSelected(true);
            this.LibrosyCatalogos.setSelected(false);
            this.Dispositivos.setSelected(false);
            this.Configuracion.setSelected(false);
            rSPanelsSlider1.setPanelSlider(20, pnl2, RSPanelsSlider.DIRECT.RIGHT);

            jPanel5.setBackground(new Color(102, 0, 153));
            jPanel6.setBackground(new Color(153, 0, 255));
            jPanel7.setBackground(new Color(102, 0, 153));
            jPanel8.setBackground(new Color(102, 0, 153));
            jPanel4.setBackground(new Color(102, 0, 153));

            mostrar();

        }
    }//GEN-LAST:event_RegistroActionPerformed

    private void LibrosyCatalogosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LibrosyCatalogosActionPerformed
        if (!this.LibrosyCatalogos.isSelected()) {
            this.Inicio.setSelected(false);
            this.Registro.setSelected(false);
            this.LibrosyCatalogos.setSelected(true);
            this.Dispositivos.setSelected(false);
            this.Configuracion.setSelected(false);
            rSPanelsSlider1.setPanelSlider(20, pnl3, RSPanelsSlider.DIRECT.RIGHT);

            jPanel5.setBackground(new Color(102, 0, 153));
            jPanel6.setBackground(new Color(102, 0, 153));
            jPanel7.setBackground(new Color(153, 0, 255));
            jPanel8.setBackground(new Color(102, 0, 153));
            jPanel4.setBackground(new Color(102, 0, 153));

            mostrarlibros();

        }

    }//GEN-LAST:event_LibrosyCatalogosActionPerformed

    private void DispositivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DispositivosActionPerformed
        if (!this.Dispositivos.isSelected()) {
            this.Inicio.setSelected(false);
            this.Registro.setSelected(false);
            this.LibrosyCatalogos.setSelected(false);
            this.Dispositivos.setSelected(true);
            this.Configuracion.setSelected(false);
            rSPanelsSlider1.setPanelSlider(20, pnl4, RSPanelsSlider.DIRECT.RIGHT);

            jPanel5.setBackground(new Color(102, 0, 153));
            jPanel6.setBackground(new Color(102, 0, 153));
            jPanel7.setBackground(new Color(102, 0, 153));
            jPanel8.setBackground(new Color(153, 0, 255));
            jPanel4.setBackground(new Color(102, 0, 153));

        }
    }//GEN-LAST:event_DispositivosActionPerformed

    private void ConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfiguracionActionPerformed
        if (!this.Configuracion.isSelected()) {
            this.Inicio.setSelected(false);
            this.Registro.setSelected(false);
            this.LibrosyCatalogos.setSelected(false);
            this.Dispositivos.setSelected(false);
            this.Configuracion.setSelected(true);
            rSPanelsSlider1.setPanelSlider(20, pnl5, RSPanelsSlider.DIRECT.RIGHT);

            jPanel5.setBackground(new Color(102, 0, 153));
            jPanel6.setBackground(new Color(102, 0, 153));
            jPanel7.setBackground(new Color(102, 0, 153));
            jPanel8.setBackground(new Color(102, 0, 153));
            jPanel4.setBackground(new Color(153, 0, 255));

        }
    }//GEN-LAST:event_ConfiguracionActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        try {

            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            // FileInputStream archivofoto;
            ps = con.prepareStatement("INSERT INTO usuario(tipodoc, doc, nombres, apellidos, tipo_usuario, grado, ano, telefono, celular, email, fecha_registro, sexo, nomimagen) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
            ps.setString(1, tipoId.getSelectedItem().toString());
            ps.setString(2, numId.getText());
            ps.setString(3, nombres.getText());
            ps.setString(4, apellidos.getText());
            ps.setString(5, tipoUsuario.getSelectedItem().toString());
            ps.setString(6, grado.getSelectedItem().toString());
            ps.setString(7, ano.getSelectedItem().toString());
            ps.setString(8, telefono.getText());
            ps.setString(9, celular.getText());
            ps.setString(10, email.getText());
            ps.setString(11, fecha_registro.getText());
            ps.setString(12, sexo.getSelectedItem().toString());
            String ruta = "D:\\eclipse-workspace\\Proyecto9_SignUp\\src\\imagenes\\estudiante.png";
            if ("".equals(rutaImagen.getText())) {
                ps.setString(13, ruta);
            } else {
                ps.setString(13, rutaImagen.getText());
            }
            // JOptionPane.showMessageDialog(null, ps.toString());
            //JOptionPane.showMessageDialog(null, ruta);   
            int res = ps.executeUpdate();
            if (res > 0) {
                mostrar();
                JOptionPane.showMessageDialog(null, "Informacion almacenada");
            } else {
                JOptionPane.showMessageDialog(null, "Informacion no pudo ser almacenada");
            }
            con.close();

            limpiarcasillas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("select * from usuario where doc = ? and tipodoc=?");
            ps.setString(1, numId.getText());
            ps.setString(2, tipoId.getSelectedItem().toString());
            res = ps.executeQuery();

            if (res.next()) {

                //tipoId.setSelectedItem(res.getString("tipodoc"));
                // numId.setText(res.getString("doc"));
                nombres.setText(res.getString("nombres"));
                apellidos.setText(res.getString("apellidos"));
                tipoUsuario.setSelectedItem(res.getString("tipo_usuario"));
                grado.setSelectedItem(res.getString("grado"));
                ano.setSelectedItem(res.getString("ano"));
                telefono.setText(res.getString("telefono"));
                celular.setText(res.getString("celular"));
                email.setText(res.getString("email"));
                fecha_registro.setText(res.getString("fecha_registro"));
                sexo.setSelectedItem(res.getString("sexo"));
                rutaImagen.setText(res.getString("nomImagen"));

                Image foto = getToolkit().getImage(res.getString("nomImagen"));

                foto = foto.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
                lblFoto.setIcon(new ImageIcon(foto));

                JOptionPane.showMessageDialog(null, "Usuario Encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario No Encontrado");
            }
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        Connection con = null;
        con = getConection();
        try {
            if ((numId.getText() == null) || (tipoId.getSelectedItem().toString().equals("Seleccione"))) {
                JOptionPane.showMessageDialog(null, "Se debe digitar el tipo de Documento y el Numero de Identificación");
                con.close();

            } else {

                PreparedStatement ps;
                ps = con.prepareStatement("delete from usuario where doc = ? and tipodoc=?");
                ps.setInt(1, Integer.parseInt(numId.getText()));
                ps.setString(2, tipoId.getSelectedItem().toString());
                int res = ps.executeUpdate();

                if (res > 0) {

                    JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente");
                    limpiarcasillas();
                    mostrar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error Al Eliminar el Usuario");
                }
            }
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }

    }//GEN-LAST:event_EliminarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ps = con.prepareStatement("Update usuario set tipodoc=?, nombres=?, apellidos=?, tipo_usuario=?, grado=?, ano=?, telefono=?, celular=?, Email=?, fecha_registro=?, sexo=?, nomimagen=? where doc=?");
            ps.setString(1, tipoId.getSelectedItem().toString());
            ps.setString(2, nombres.getText());
            ps.setString(3, apellidos.getText());
            ps.setString(4, tipoUsuario.getSelectedItem().toString());
            ps.setString(5, grado.getSelectedItem().toString());
            ps.setString(6, ano.getSelectedItem().toString());
            ps.setString(7, telefono.getText());
            ps.setString(8, celular.getText());
            ps.setString(9, email.getText());
            ps.setString(10, fecha_registro.getText());
            ps.setString(11, sexo.getSelectedItem().toString());
            ps.setString(12, rutaImagen.getText());
            ps.setString(13, numId.getText());
            System.err.println(ps.toString());
            int res = ps.executeUpdate();
            if (res > 0) {
                mostrar();
                JOptionPane.showMessageDialog(null, "Informacion Modificada");
            } else {
                JOptionPane.showMessageDialog(null, "Informacion no pudo ser Modificada");
            }
            con.close();

            limpiarcasillas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarcasillas();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void jBReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBReporteActionPerformed
        if (!this.jBReporte.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl8, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_jBReporteActionPerformed

    private void jBPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrestamoActionPerformed
        if (!this.jBPrestamo.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl6, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_jBPrestamoActionPerformed

    private void jBEstadisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEstadisticaActionPerformed
        if (!this.jBEstadistica.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl7, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_jBEstadisticaActionPerformed

    private void Regresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regresar1ActionPerformed
        if (!this.Regresar1.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl1, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_Regresar1ActionPerformed

    private void Regresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regresar2ActionPerformed
        if (!this.Regresar2.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl1, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_Regresar2ActionPerformed

    private void Regrear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regrear3ActionPerformed
        if (!this.Regrear3.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl1, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_Regrear3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        lblFoto.setText(null);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de Archivos JPEG(*.JPG;*JPEG)", "jpg", "jpeg");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\hcholes\\Pictures");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivo.getSelectedFile();
            rutaImagen.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(rutaImagen.getText());
            foto = foto.getScaledInstance(250, 300, Image.SCALE_DEFAULT);
            lblFoto.setIcon(new ImageIcon(foto));
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBPrestamo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrestamo1ActionPerformed
        if (!this.jBPrestamo.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl9, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_jBPrestamo1ActionPerformed

    private void Regresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regresar3ActionPerformed
        if (!this.Regrear3.isSelected()) {

            rSPanelsSlider1.setPanelSlider(20, pnl1, RSPanelsSlider.DIRECT.RIGHT);

        }
    }//GEN-LAST:event_Regresar3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("select * from librosycatalogos2 where codigo = ?");
            ps.setString(1, codigolibro.getText());

            res = ps.executeQuery();

            if (res.next()) {

                nombre_libro.setText(res.getString("nombre"));
                area.setText(res.getString("area"));
                codAutor.setText(res.getString("cod_autor"));
                cod_editorial.setText(res.getString("cod_editorial"));
                ano1.setSelectedItem(res.getString("año"));
                tomo.setSelectedItem(res.getString("tomo"));
                observacion.setText(res.getString("observacion"));
                fecha_registro1.setText(res.getString("fecha"));
                rutaImagen1.setText(res.getString("rutaimagen"));

                Image foto = getToolkit().getImage(res.getString("rutaimagen"));

                foto = foto.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
                lblFoto1.setIcon(new ImageIcon(foto));

                JOptionPane.showMessageDialog(null, "Libro Encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Libro No Encontrado");
            }
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        lblFoto1.setText(null);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de Archivos JPEG(*.JPG;*JPEG)", "jpg", "jpeg");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\hcholes\\Pictures");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivo.getSelectedFile();
            rutaImagen1.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(rutaImagen1.getText());
            foto = foto.getScaledInstance(250, 300, Image.SCALE_DEFAULT);
            lblFoto1.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void Limpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar1ActionPerformed
        limpiarcasillas2();
    }//GEN-LAST:event_Limpiar1ActionPerformed

    private void Modificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar1ActionPerformed
        try {
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ps = con.prepareStatement("Update librosycatalogos2 set nombre=?, area=?, cod_autor=?, cod_editorial=?, año=?, tomo=?, fecha=?, observacion=?, rutaimagen=? where codigo=?");
            ps.setString(1, nombre_libro.getText());
            ps.setString(2, area.getText());
            ps.setString(3, codAutor.getText());
            ps.setString(4, cod_editorial.getText());
            ps.setString(5, ano1.getSelectedItem().toString());
            ps.setString(6, tomo.getSelectedItem().toString());
            ps.setString(7, fecha_registro1.getText());
            ps.setString(8, observacion.getText());

            ps.setString(9, rutaImagen1.getText());
            ps.setString(10, codigolibro.getText());
            observacion.setText(ps.toString());
            int res = ps.executeUpdate();
            if (res > 0) {
                mostrarlibros();
                JOptionPane.showMessageDialog(null, "Informacion Modificada");
            } else {
                JOptionPane.showMessageDialog(null, "Informacion no pudo ser Modificada");
            }
            con.close();

            limpiarcasillas2();
        } catch (HeadlessException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }
    }//GEN-LAST:event_Modificar1ActionPerformed

    private void Eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar1ActionPerformed

        Connection con = null;

        con = getConection();

        try {
            if (codigolibro.getText() == null) {
                JOptionPane.showMessageDialog(null, "Se debe digitar el codigo del libro");
                con.close();

            } else {

                PreparedStatement ps;
                ps = con.prepareStatement("delete from librosycatalogos2 where codigo = ?");
                ps.setString(1, codigolibro.getText());

                int res = ps.executeUpdate();

                if (res > 0) {

                    JOptionPane.showMessageDialog(null, "Registro del Libro Eliminado Exitosamente");
                    limpiarcasillas2();
                    mostrarlibros();
                } else {
                    JOptionPane.showMessageDialog(null, "Error Al Eliminar el Registro del Libro");
                }
            }
            con.close();
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }
    }//GEN-LAST:event_Eliminar1ActionPerformed

    private void Guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar1ActionPerformed
        try {

            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            // FileInputStream archivofoto;
            ps = con.prepareStatement("INSERT INTO librosycatalogos2(codigo, nombre, area, cod_autor, cod_editorial, año, tomo, fecha, observacion, rutaimagen) VALUES (?,?,?,?,?,?,?,?,?,?) ");
            ps.setString(1, codigolibro.getText());
            ps.setString(2, nombre_libro.getText());
            ps.setString(3, area.getText());
            ps.setString(4, codAutor.getText());
            ps.setString(5, cod_editorial.getText());
            ps.setString(6, ano1.getSelectedItem().toString());
            ps.setString(7, tomo.getSelectedItem().toString());
            ps.setString(8, fecha_registro1.getText());
            ps.setString(9, observacion.getText());

            String ruta = "D:\\eclipse-workspace\\Proyecto9_SignUp\\src\\imagenes\\libro.png";
            if ("".equals(rutaImagen1.getText())) {
                ps.setString(10, ruta);
            } else {
                ps.setString(10, rutaImagen1.getText());
            }
System.out.println(ps.toString());
            int res = ps.executeUpdate();
            if (res > 0) {
                mostrarlibros();
                JOptionPane.showMessageDialog(null, "Informacion del Libro almacenada");
            } else {
                JOptionPane.showMessageDialog(null, "Informacion no pudo ser almacenada");
            }
            con.close();

            limpiarcasillas2();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }

    }//GEN-LAST:event_Guardar1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        rSPanelsSlider1.setPanelSlider(20, autores, RSPanelsSlider.DIRECT.RIGHT);

        mostrarautores();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        rSPanelsSlider1.setPanelSlider(20, editorial, RSPanelsSlider.DIRECT.RIGHT);

        mostrareditoriales();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void Regresar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regresar4ActionPerformed
        rSPanelsSlider1.setPanelSlider(20, pnl3, RSPanelsSlider.DIRECT.RIGHT);

        DefaultTableModel tm = (DefaultTableModel) tabla_autores.getModel();

        String dato = String.valueOf(tm.getValueAt(tabla_autores.getSelectedRow(), 0));
        codAutor.setText(dato);

    }//GEN-LAST:event_Regresar4ActionPerformed

    private void Regresar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regresar5ActionPerformed
        rSPanelsSlider1.setPanelSlider(20, pnl3, RSPanelsSlider.DIRECT.RIGHT);
        DefaultTableModel tm = (DefaultTableModel) tabla_editorial.getModel();

        String dato = String.valueOf(tm.getValueAt(tabla_editorial.getSelectedRow(), 0));
        cod_editorial.setText(dato);

    }//GEN-LAST:event_Regresar5ActionPerformed

    private void Limpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar2ActionPerformed
        limpiareditorial();
    }//GEN-LAST:event_Limpiar2ActionPerformed

    private void Modificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Modificar2ActionPerformed

    private void Eliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Eliminar2ActionPerformed

    private void Guardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar2ActionPerformed
        try {

            if ("".equals(codi_editorial.getText())){
                codi_editorial.requestFocus();
                JOptionPane.showMessageDialog(null, "SE DEBE DIGITAR EL CODIGO DE LA EDITORIAL");
            }
            else if("".equals(nomb_editorial.getText())){
                nomb_editorial.requestFocus();
                
                JOptionPane.showMessageDialog(null, "SE DEBE DIGITAR EL NOMBRE DE LA EDITORIAL");
            }
            else {
                
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            // FileInputStream archivofoto;
            ps = con.prepareStatement("INSERT INTO editorial2(Cod_editorial, nom_editorial, fecha_registro) VALUES (?,?,?) ");
            
            ps.setString(1, codi_editorial.getText());
            ps.setString(2, nomb_editorial.getText());
            ps.setString(3, fecha_registro2.getText());
            int res = ps.executeUpdate();
            if (res > 0) {
                mostrareditoriales();
                JOptionPane.showMessageDialog(null, "Informacion almacenada");
            } else {
                JOptionPane.showMessageDialog(null, "Informacion no pudo ser almacenada");
            }
            con.close();

            limpiareditorial();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el proceso");
        }

    }//GEN-LAST:event_Guardar2ActionPerformed

    private void ReportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportarPDFActionPerformed
       
        try{
        Connection con=getConection();
        JasperReport reporte=null;
        String path="src\\Reportes\\Usuarios.jasper";
        reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
        JasperPrint jprint= JasperFillManager.fillReport(reporte, null, con);
        
        JasperViewer vista=new JasperViewer(jprint, false);
        
        vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        vista.setVisible(true);
        }catch(JRException e){
            
            JOptionPane.showMessageDialog(null, e);
        }        
        
        
    }//GEN-LAST:event_ReportarPDFActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Configuracion;
    private javax.swing.JButton Dispositivos;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Eliminar1;
    private javax.swing.JButton Eliminar2;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Guardar1;
    private javax.swing.JButton Guardar2;
    private javax.swing.JButton Inicio;
    private javax.swing.JButton LibrosyCatalogos;
    private javax.swing.JButton Limpiar;
    private javax.swing.JButton Limpiar1;
    private javax.swing.JButton Limpiar2;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Modificar1;
    private javax.swing.JButton Modificar2;
    private javax.swing.JLabel NOMBREEDITO;
    private javax.swing.JLabel NOMBREEDITO1;
    private javax.swing.JLabel NOMBREEDITO2;
    private javax.swing.JButton Registro;
    private javax.swing.JButton Regrear3;
    private javax.swing.JButton Regresar1;
    private javax.swing.JButton Regresar2;
    private javax.swing.JButton Regresar3;
    private javax.swing.JButton Regresar4;
    private javax.swing.JButton Regresar5;
    private javax.swing.JButton ReportarPDF;
    private javax.swing.JComboBox<String> ano;
    private javax.swing.JComboBox<String> ano1;
    private javax.swing.JTextField apellidos;
    private javax.swing.JTextField area;
    private javax.swing.JPanel autores;
    private javax.swing.JLabel cant_autores;
    private javax.swing.JLabel cant_editorial;
    private javax.swing.JLabel cant_libros;
    private javax.swing.JLabel cant_usuarios;
    private javax.swing.JTextField celular;
    private javax.swing.JTextField codAutor;
    private javax.swing.JTextField cod_editorial;
    private javax.swing.JTextField codi_editorial;
    private javax.swing.JTextField codigolibro;
    private javax.swing.JPanel editorial;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fecha_registro;
    private javax.swing.JTextField fecha_registro1;
    private javax.swing.JTextField fecha_registro2;
    private javax.swing.JComboBox<String> grado;
    private javax.swing.JButton jBEstadistica;
    private javax.swing.JButton jBPrestamo;
    private javax.swing.JButton jBPrestamo1;
    private javax.swing.JButton jBReporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblFoto1;
    private javax.swing.JTextField nomb_editorial;
    private javax.swing.JTextField nombre_libro;
    private javax.swing.JTextField nombres;
    private javax.swing.JTextField numId;
    private javax.swing.JTextArea observacion;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl8;
    private javax.swing.JPanel pnl9;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private javax.swing.JTextField rutaImagen;
    private javax.swing.JTextField rutaImagen1;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JLabel sexo1;
    private javax.swing.JPanel sidepane;
    private javax.swing.JTable tabla_autores;
    private javax.swing.JTable tabla_editorial;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox<String> tipoId;
    private javax.swing.JComboBox<String> tipoUsuario;
    private javax.swing.JLabel titulo_listaautores;
    private javax.swing.JComboBox<String> tomo;
    // End of variables declaration//GEN-END:variables

}
