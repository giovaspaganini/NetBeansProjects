package rh.visao;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCargo extends JFrame {
    private JTextField jtfNome = new JTextField(10);
    private JLabel lbMsg = new JLabel();
    
    public TelaCargo() throws HeadlessException {
        super("Cadastro/Alteração de Cargos");
        this.setLayout(new FlowLayout());
        Container p = this.getContentPane();
        
        p.add(new JLabel("Nome: "));
        
        p.add(jtfNome);
        
        JButton btOk = new JButton("Me aperte!");        
        p.add(btOk);
                
        p.add(lbMsg);
        
        btOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        lbMsg.setText("Boa noite ".concat(jtfNome.getText()));
                    }
        }
        
        
        
        );
        
        
        this.setSize(400,400);        
        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }
}
