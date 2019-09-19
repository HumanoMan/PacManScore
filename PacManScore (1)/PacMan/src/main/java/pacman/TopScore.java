/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JComponent;

/**
 *
 * @author desenv11
 */
public class TopScore extends JComponent{
    public UserScore uScore[] = new UserScore[10];
    UserScore us = new UserScore();
    public TopScore(){
        
    }
    public void loadData(){
        File arq = new File("/home/desenv11/Documentos/game.txt");// Caminho
        FileInputStream input;
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try{
            input = new FileInputStream(arq);
            while (true) {
                int c = input.read(); 
                if (c == -1) { // se o valor for < 0 o laço para
                    break;
                }
                b.write(c);
            }
            String placar = new String(b.toByteArray());
            String linhas[] = placar.split("\n");
            int i = 0;
            for (String linha : linhas) {
                String coluna[] = linha.split(" = ");
                UserScore userScore = new UserScore();
                userScore.name = coluna[0];
                userScore.score = Integer.parseInt(coluna[1]);
                uScore[i] = userScore;
                i++;
                if(i > 9){
                    break;
                }
            }
            }
        catch(FileNotFoundException ex){ // Não achou o diretório
                System.out.println(";-; não foi possivel realizar seu Save Erro Nº 1");
            }
            catch(IOException ex){ // Outras ocasiões de entrada e saida
                System.out.println(";-; não foi possivel realizar seu Save Erro Nº 2");
            }
    }
    @Override
    public void paint(Graphics g){
        int i = 0;
        if (uScore != null) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 800, 80);
                g.fillRect(0, 0, 120, 600);
                g.fillRect(660, 0, 140, 600);
                g.fillRect(0, 520, 800, 80);
                g.setColor(Color.RED);
                g.fillRect(120, 50, 30, 470);
                g.fillRect(660, 50, 30, 470);
                g.fillRect(120, 490, 570, 30);
                g.fillRect(120, 50, 570, 30);
                g.setColor(Color.BLACK);
                g.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 31));
                g.drawString("TOP 10 PLAYERS", 260, 120);
                g.setColor(Color.YELLOW);
                g.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 30));
                g.drawString("TOP 10 PLAYERS", 260, 120);
            for (UserScore userS: uScore) {
                if (g != null && userS != null) { // Exibe os 10 jogadores
                g.setColor(Color.BLACK);
                g.setFont(new Font("Chilanka", Font.BOLD, 30));
                g.drawString(userS.name + " = " + userS.score, 175, 180+i*30);
                g.setColor(Color.CYAN);
                g.setFont(new Font("Chilanka", Font.BOLD, 29));
                g.drawString(userS.name + " = " + userS.score, 180, 180+i*30);
                }
                i++;
            }
        }
    }
    public void returnGame(){
        // volta pro game
    }
    public void save(){
            File text = new File("/home/desenv11/Documentos/game.txt");// Caminho
            FileOutputStream out;
            String score = Integer.toString(us.score); // Converte o us.score para o tipo String
            String name = us.name;
            try{
            out = new FileOutputStream(text);// Onde guardar 
            out.write(name.getBytes()); // O que guardar
            out.write(score.getBytes());// Add o tipo int convertido pra String
            out.close(); // termina
            }
            catch(FileNotFoundException ex){ // Não achou o diretório
                System.out.println(";-; não foi possivel realizar seu Save Erro Nº 1");
            }
            catch(IOException ex){ // Outras ocasiões de entrada e saida
                System.out.println(";-; não foi possivel realizar seu Save Erro Nº 2");
            }
    }
}
