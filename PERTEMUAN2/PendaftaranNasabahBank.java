package PERTEMUAN2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PendaftaranNasabahBank extends JFrame {
    // Deklarasi komponen
    private JTextField namaTextField;
    private JPasswordField passwordField, konfirmasiPasswordField;
    private JList<String> listTabungan;
    private JSpinner spinnerTransaksi, spinnerTanggal;
    private JTextArea textAreaOutput;

    public PendaftaranNasabahBank() {
        setTitle("Form Pendaftaran Nasabah Bank");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Menempatkan frame di tengah layar

        // Membuat panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Input nama
        panel.add(new JLabel("Nama:"));
        namaTextField = new JTextField(20);
        panel.add(namaTextField);

        // Input jenis tabungan menggunakan JList
        panel.add(new JLabel("Jenis Tabungan:"));
        String[] jenisTabungan = {"Tabungan Reguler", "Tabungan Premium", "Tabungan Pemuda", "Tabungan Bisnis"};
        listTabungan = new JList<>(jenisTabungan);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPaneTabungan = new JScrollPane(listTabungan);
        panel.add(scrollPaneTabungan);

        // Input frekuensi transaksi menggunakan JSpinner
        panel.add(new JLabel("Frekuensi Transaksi (per bulan):"));
        SpinnerModel modelTransaksi = new SpinnerNumberModel(1, 1, 100, 1); // Nilai awal, min, max, step
        spinnerTransaksi = new JSpinner(modelTransaksi);
        panel.add(spinnerTransaksi);

        // Input password dan konfirmasi password
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        panel.add(new JLabel("Konfirmasi Password:"));
        konfirmasiPasswordField = new JPasswordField(20);
        panel.add(konfirmasiPasswordField);

        // Input tanggal lahir menggunakan JSpinner
        panel.add(new JLabel("Tanggal Lahir:"));
        SpinnerModel modelTanggal = new SpinnerDateModel();
        spinnerTanggal = new JSpinner(modelTanggal);
        JSpinner.DateEditor editorTanggal = new JSpinner.DateEditor(spinnerTanggal, "dd/MM/yyyy");
        spinnerTanggal.setEditor(editorTanggal);
        panel.add(spinnerTanggal);

        // Tombol Kirim
        JButton tombolSubmit = new JButton("Kirim");
        tombolSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirimData();
            }
        });
        panel.add(tombolSubmit);

        // Area output
        textAreaOutput = new JTextArea(10, 30);
        textAreaOutput.setEditable(false);
        panel.add(new JScrollPane(textAreaOutput));

        // Menambahkan menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opsi");
        JMenuItem menuReset = new JMenuItem("Reset");
        JMenuItem menuExit = new JMenuItem("Keluar");

        // Reset action
        menuReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Exit action
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(menuReset);
        menu.add(menuExit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        add(panel);
        setVisible(true);
    }

    // Method untuk mengirim data dan memeriksa kecocokan password
    private void kirimData() {
        char[] password = passwordField.getPassword();
        char[] konfirmasiPassword = konfirmasiPasswordField.getPassword();

        textAreaOutput.setText(""); // Menghapus output sebelumnya

        if (Arrays.equals(password, konfirmasiPassword)) {
            textAreaOutput.append("Password cocok\n");
        } else {
            textAreaOutput.append("Password tidak cocok\n");
            return; // Keluar jika password tidak cocok
        }

        String nama = namaTextField.getText();
        String jenisTabungan = listTabungan.getSelectedValue();
        int frekuensiTransaksi = (int) spinnerTransaksi.getValue();
        Date tanggalLahir = (Date) spinnerTanggal.getValue();

        // Format tanggal agar hanya menampilkan dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String tanggalFormatted = dateFormat.format(tanggalLahir);

        // Menampilkan hasil inputan
        textAreaOutput.append("Nama: " + nama + "\n");
        textAreaOutput.append("Jenis Tabungan: " + jenisTabungan + "\n");
        textAreaOutput.append("Frekuensi Transaksi: " + frekuensiTransaksi + " kali per bulan\n");
        textAreaOutput.append("Tanggal Lahir: " + tanggalFormatted + "\n");
    }

    // Method untuk reset form
    private void resetForm() {
        namaTextField.setText("");
        passwordField.setText("");
        konfirmasiPasswordField.setText("");
        listTabungan.clearSelection();
        spinnerTransaksi.setValue(1);
        spinnerTanggal.setValue(new Date());
        textAreaOutput.setText("");
    }

    public static void main(String[] args) {
        new PendaftaranNasabahBank();
    }
}
