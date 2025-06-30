package view;

import com.formdev.flatlaf.FlatLightLaf;
import main.java.services.cidades.ClockUpdater;


import view.panel.GradientPanel;
import view.panel.PanelFactory;
import view.theme.ThemeManager;
import view.panel.MenuFactory;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Clima.*;
import services.cidades.*;
import model.*;
import services.*;
import dal.*;


public class DashboardView extends JFrame {


    private JComboBox<Estado> comboEstado;
    private JComboBox<Cidade> comboCidade;

    public JComboBox<String> comboLocalizacao;
    public JLabel rotuloDataHora;
    public JTextArea areaTempoAtual;
    public JTextArea areaPrevisaoHoras;
    public JTextArea areaPrevisaoDias;
    public JTextArea areaIndiceUV;  // Novo widget: Índice UV

    public DashboardView() {
        setTitle("Painel de Clima");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setJMenuBar(criarMenuBar());
        inicializarComponentes();
    }

    private JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        //menuBar.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
        JMenu menuArquivo = new JMenu("Arquivo");
        MenuFactory.decorate(menuArquivo,1,8);
        JMenuItem itemDashboard = new JMenuItem("Dashboard");
        itemDashboard.addActionListener(e -> acaoDashboard());
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemDashboard);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);

        JMenu menuDados = new JMenu("Dados");
        MenuFactory.decorate(menuDados,1,8);
        JMenuItem itemAtualizar = new JMenuItem("Atualizar dados");
        itemAtualizar.addActionListener(e -> acaoAtualizarDados());
        JMenuItem itemHistorico = new JMenuItem("Histórico");
        itemHistorico.addActionListener(e -> acaoHistorico());
        menuDados.add(itemAtualizar);
        menuDados.add(itemHistorico);

        JMenu menuConfiguracoes = new JMenu("Configurações");
        MenuFactory.decorate(menuConfiguracoes,1,8);
        JMenuItem itemPreferencias = new JMenuItem("Preferências");
        itemPreferencias.addActionListener(e -> acaoPreferencias());
        menuConfiguracoes.add(itemPreferencias);

        JMenu menuAjuda = new JMenu("Ajuda");
        MenuFactory.decorate(menuAjuda,1,8);
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> acaoSobre());
        menuAjuda.add(itemSobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuDados);
        menuBar.add(menuConfiguracoes);
        menuBar.add(menuAjuda);

        return menuBar;
    }

    private void inicializarComponentes() {
        // Topo: localização e data/hora
        JPanel painelTopo = new JPanel(new BorderLayout(10, 10));
        JPanel painelLocal = new JPanel(new FlowLayout(FlowLayout.LEFT,8,0));


        painelLocal.add(new JLabel("Localização:"));

        //parametros de teste para combo
        //String[] locais = {"Santa Cruz do Sul", "Porto Alegre", "São Paulo"};
        //comboLocalizacao = new JComboBox<>(locais);
        //painelLocal.add(comboLocalizacao);

        comboEstado = new JComboBox<>();
        comboCidade = new JComboBox<>();

        carregarEstados();
        painelLocal.add(new JLabel("Estado:"));
        painelLocal.add(comboEstado);
        painelLocal.add(new JLabel("Cidade:"));
        painelLocal.add(comboCidade);

        painelTopo.add(painelLocal, BorderLayout.WEST);

        rotuloDataHora = new JLabel();
        painelTopo.add(rotuloDataHora, BorderLayout.EAST);
        ClockUpdater.start(rotuloDataHora);
        add(painelTopo, BorderLayout.NORTH);

        // Painel principal 2x2, com 4 widgets
        GradientPanel painelPrincipal = new GradientPanel();
        painelPrincipal.setLayout(new GridLayout(2, 2, 10, 10));
        areaTempoAtual = criarAreaTexto("Carregando Tempo Atual...");
        areaPrevisaoHoras = criarAreaTexto("Carregando Previsão Horária...");
        areaPrevisaoDias = criarAreaTexto("Carregando Previsão Diária...");
        areaIndiceUV = criarAreaTexto("Carregando Índice UV...");  // Widget UV

        painelPrincipal.add(PanelFactory.criarPainelComBorda("Tempo Atual", areaTempoAtual));
        painelPrincipal.add(PanelFactory.criarPainelComBorda("Próximas Horas", areaPrevisaoHoras));
        painelPrincipal.add(PanelFactory.criarPainelComBorda("Próximos Dias", areaPrevisaoDias));
        painelPrincipal.add(PanelFactory.criarPainelComBorda("Índice UV", areaIndiceUV));

        add(painelPrincipal, BorderLayout.CENTER);
    }



    private JTextArea criarAreaTexto(String textoInicial) {
        JTextArea area = new JTextArea(textoInicial);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }


    // Métodos de ação dos menus (exemplo)
    private void acaoDashboard() {
        JOptionPane.showMessageDialog(this, "Dashboard selecionado.");
    }

    private void acaoAtualizarDados() {

        Cidade cidade = (Cidade) comboCidade.getSelectedItem();
        if (cidade != null) {
            Coordenada coordenada = new CidadeService().buscarOuCarregarCoordenadas(cidade);
            if (coordenada != null) {
                ClimaAtual clima = new ClimaService().buscarClimaAtual(coordenada.getLatitude(), coordenada.getLongitude());
                atualizarClimaAtual(clima);
            }
        }



        JOptionPane.showMessageDialog(this, "Dados atualizados.");
    }

    private void acaoHistorico() {
        JOptionPane.showMessageDialog(this, "Exibindo histórico.");
    }

    private void acaoPreferencias() {
        JOptionPane.showMessageDialog(this, "Abrindo preferências.");
    }

    private void acaoSobre() {
        JOptionPane.showMessageDialog(this, "ClimaBagual v1.0\nDesenvolvido por Sua Equipe", "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    private void carregarEstados(){


        List<Estado> estados = new EstadoDAO().listarEstados();


        for (Estado e : estados) {
            comboEstado.addItem(e);
        }
        comboEstado.addActionListener(e -> {
            Estado estadoSelecionado = (Estado) comboEstado.getSelectedItem();
            if (estadoSelecionado != null) {
                comboCidade.removeAllItems();
                List<Cidade> cidades = new CidadeDAO().listarPorEstado(estadoSelecionado.getId());
                for (Cidade c : cidades) {
                    comboCidade.addItem(c);
                }
            }
        });

    }

    public void atualizarClimaAtual(ClimaAtual clima) {
        if (clima == null) {
            areaTempoAtual.setText("Erro ao carregar tempo atual.");
            return;
        }

        String texto = String.format("""
        Temperatura: %.1f°C
        Vento: %.1f km/h
        Condição: %s
        """, clima.getTemperatura(), clima.getVento(), clima.getDescricao());

        areaTempoAtual.setText(texto);
    }


    public static void main(String[] args) {
        ThemeManager.initTheme();

        SwingUtilities.invokeLater(() -> {
            DashboardView view = new DashboardView();
            view.getRootPane().setBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            );

            view.setVisible(true);
        });
    }
}
