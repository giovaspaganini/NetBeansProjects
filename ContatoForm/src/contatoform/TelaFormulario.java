package contatoform;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaFormulario extends JFrame{
    private JTextField  jtfNome = new JTextField(10);
    private JTextField  jtfEmail = new JTextField(30);
    private JTextField  jtfTel = new JTextField(15);    
    private JComboBox<String> cbAssunto = new JComboBox<>();
    private JCheckBox cxMaioridade = new JCheckBox("Confirmo que sou maior de 18 anos!");
    
    private JLabel lbMsg = new JLabel();
    private JLabel lbResp = new JLabel();    
    
    private JRadioButton rbAmigo = new JRadioButton("Um amigo");
    private JRadioButton rbAnuncio = new JRadioButton("Um anuncio na internet");
    private JRadioButton rbSozinho = new JRadioButton("Encontrei sozinho");
    private JTextArea taMensagem = new JTextArea(5, 45);    
    
    public TelaFormulario() throws HeadlessException {
        super("Formulário de Contato");
        this.setLayout(new FlowLayout());
        Container p = this.getContentPane();
        
        p.setLayout(new GridLayout(0, 1));
        p.add(new JLabel("\nNome: "));
        p.add(jtfNome);        
        
        p.add(new JLabel("\nE-m@il: "));
        p.add(jtfEmail);
        
        p.add(new JLabel("\nTelefone: "));
        p.add(jtfTel);  
        
        p.add(new JLabel("\nAssunto: "));
        cbAssunto.addItem("Planos comerciais");
        cbAssunto.addItem("Suporte");
        cbAssunto.addItem("Outros");
        p.add(cbAssunto);
        
        p.add(new JLabel("\nSua Mensagem: "));
        p.add(taMensagem);        
        
        p.add(new JLabel("\nConfirme sua idade*"));
        p.add(cxMaioridade);
        cxMaioridade.isSelected(); //saber o que esta marcado; boolean
        
        p.add(rbAmigo);
        p.add(rbAnuncio);
        p.add(rbSozinho);
        
        ButtonGroup bgZona = new ButtonGroup();
        bgZona.add(rbAmigo);
        bgZona.add(rbAnuncio);
        bgZona.add(rbSozinho);
        
        JButton btEnviar = new JButton("Enviar");
        p.add(btEnviar);
        
        p.add(lbMsg);
        p.add(lbResp);

        btEnviar.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String validaNome = jtfNome.getText();                
                if (validaNome.isEmpty()) {
                    lbResp.setText("Nome inválido");
                    return;
                }
                
                String validaEmail = jtfEmail.getText();
                if (validaEmail.isEmpty()){
                    lbResp.setText("Email inválido!");
                    return;
                }
                
                String validaTelefone = jtfTel.getText();
                if (validaTelefone.isEmpty()){
                    lbResp.setText("Telefone inválido!");
                    return;
                }
                
                String validaMsg = jtfTel.getText();
                if (validaMsg.isEmpty()){
                    lbResp.setText("Mensagem inválida!");
                    return;
                }
                
                if (cbAssunto.getSelectedIndex() == 0){
                    lbResp.setText("Obrigado, ".concat(jtfNome.getText())
                    .concat(", nossa equipe de vendas entrará em contato através do telefone providenciado.")
                    );
                } 
                if (cbAssunto.getSelectedIndex() == 1){
                    lbResp.setText("Obrigado, ".concat(jtfNome.getText())
                    .concat(", nossa equipe de suporte entrará em contato através do telefone providenciado.")
                    );
                }
                if (cbAssunto.getSelectedIndex() == 2){
                    lbResp.setText("Obrigado, ".concat(jtfNome.getText())
                    .concat(", um atendente será designado para entrar em contato através do telefone providenciado.")
                    );
                }                 
                
                if (!cxMaioridade.isSelected()) {
                  JOptionPane.showMessageDialog(null, "Confirme sua idade!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Obrigado, seus dados foram enviados com sucesso!");
                }                
            }
        }
        
        );        
        
        this.setSize(640,640);
        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }
}

