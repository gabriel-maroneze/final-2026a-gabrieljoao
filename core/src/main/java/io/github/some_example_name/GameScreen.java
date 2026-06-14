package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input; // Ler entradas do teclado
import com.badlogic.gdx.Screen; //Interface para telas do jogo
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer; // Desenha formas (hitbox de colisão)
import com.badlogic.gdx.maps.MapLayer; //Representa uma camada genérica do Tiled
import com.badlogic.gdx.maps.MapObject; // Representa objetos genéricos do Tiled
import com.badlogic.gdx.maps.objects.RectangleMapObject; // Representa objetos retangulares do Tiled (colisões)
import com.badlogic.gdx.maps.tiled.TiledMap; // Representa o mapa carregado do Tiled
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer; // Obtém dimensões do mapa
import com.badlogic.gdx.maps.tiled.TmxMapLoader; // Carrega o tmx do tiled
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer; //Permite display de fonte na tela (descobrir posição do jogador)
import com.badlogic.gdx.math.Rectangle; //imports para visualizar a hitbox de colisões (testes)
import com.badlogic.gdx.utils.Array; //Lista dinâmica do libGDX. Para armazenar as colisões do mapa

public class GameScreen implements Screen {

    // VARIAVEIS E CONSTANTES DO JOGO

    private ShapeRenderer shapes; //Desenha formas

    // Câmera e renderização
    private OrthographicCamera camera;
    private SpriteBatch batch;

    // Viewport (controla o zoom)
    private static final float VIEWPORT_WIDTH  = 320f;
    private static final float VIEWPORT_HEIGHT = 240f;

    // Tiled Map
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer mapaRenderer;
    private static final float ESCALA_MAPA = 1f;

    // Colisões do mapa
    private Array<Rectangle> colisoes = new Array<>();

    // Spritesheets do personagem
    private Texture sheetDown, sheetUp, sheetLeft, sheetRight;
    private Animation<TextureRegion> animDown, animUp, animLeft, animRight;
    private Animation<TextureRegion> animAtual;

    // Dimensões do frame
    private static final int FRAME_COLS     = 12;
    private static final int FRAME_WIDTH    = 24;
    private static final int FRAME_HEIGHT   = 24;
    private static final float FRAME_DURATION = 0.1f;

    // Hitbox do personagem
    private Rectangle hitboxPlayer;
    // Hitbox menor que o sprite para ficar mais natural
    private static final float HITBOX_W = 12f;
    private static final float HITBOX_H = 10f;

    // Posição e velocidade
    private float playerX, playerY;
    private static final float VELOCIDADE = 70f;
    private float mapWidth, mapHeight;

    // Controle de animação
    private float stateTime = 0f;
    private boolean movendo  = false;

    //Variável para exibir texto na tela (descobrir posição do jogador)
    private BitmapFont font;

    // Sistema de entrada e saída da casa
    private Rectangle zonaEntradaCasa;
    private Rectangle zonaSaidaCasa;
    private boolean dentroDaCasa = false;
    private boolean podeInteragir = false;

    //==============================================================


    //FUNÇÕES DO CICLO DE VIDA DO JOGO

    // Inicializa jogo
    //=============================================================
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        batch = new SpriteBatch();
        shapes = new ShapeRenderer(); //Inicializa o Shape Renderer

        // Inicializa fonte para exibir a posição do jogador na tela
        font = new BitmapFont();

        // Carrega o Mapa
        mapa         = new TmxMapLoader().load("Exterior.tmx");
        mapaRenderer = new OrthogonalTiledMapRenderer(mapa, ESCALA_MAPA);

        // Dimensões do mapa
        TiledMapTileLayer camada = (TiledMapTileLayer) mapa.getLayers().get(0);
        mapWidth  = camada.getWidth()  * camada.getTileWidth();
        mapHeight = camada.getHeight() * camada.getTileHeight();

        
        criarColisoesManuais(); // Cria colisões manuais, sem usar o tiled
        zonaEntradaCasa = new Rectangle(710f, 152f, 18f, 14f); // Cria zona de interação na frente da casa
    
        // Spritesheets
        sheetDown  = new Texture("Walk_Down.png");
        sheetUp    = new Texture("Walk_Up.png");
        sheetLeft  = new Texture("Walk_Left.png");
        sheetRight = new Texture("Walk_Right.png");

        animDown  = criarAnimacao(sheetDown);
        animUp    = criarAnimacao(sheetUp);
        animLeft  = criarAnimacao(sheetLeft);
        animRight = criarAnimacao(sheetRight);
        animAtual = animDown;

        // Posição inicial e hitbo
        playerX = 722f;
        playerY = 151f;
        hitboxPlayer = new Rectangle(playerX, playerY, HITBOX_W, HITBOX_H);
    }

    // Carrega os objetos da camada "Collisions" do Tiled
    //==========================================================
    private void carregarColisoes() {
        for (MapLayer layer : mapa.getLayers()) {
            // Pula layers
            if (layer instanceof TiledMapTileLayer) continue;

            // Processa só a camada de colisões
            if (!layer.getName().equals("Collisions")) continue;

            for (MapObject object : layer.getObjects()) {
                if (object instanceof RectangleMapObject) {
                    Rectangle r = ((RectangleMapObject) object).getRectangle();
                    // Ajusta o tamanho e posição do y(hitbox) do tiled para o personagem

                    float yCorrigido = mapHeight - r.y - r.height;
                    colisoes.add(new Rectangle(r.x, yCorrigido, r.width, r.height));
                }
            }
        }
        // Contador de colisões, para testes.
        Gdx.app.log("Colisoes", "Total de colisoes carregadas: " + colisoes.size);
    }

    // Cria colisões via libGDX
    //==========================================================
     private void criarColisoesManuais() {

        // Cercas ao redor da casa
        colisoes.add(new Rectangle(588f, 107f, 118f, 8f));   // Inferior esquerda
        colisoes.add(new Rectangle(584f, 104f, 7f,   137f)); // Esquerda (vertical)
        colisoes.add(new Rectangle(584f, 233f, 204f, 8f));   // Superior
        colisoes.add(new Rectangle(787f, 113f, 6f,   120f)); // Direita (vertical)
        colisoes.add(new Rectangle(733f, 105f, 58f,  8f));   // Inferior direita

        colisoes.add(new Rectangle(620f, 160f, 130f, 70f)); //Casa

        //Bordas Inferiores
        colisoes.add(new Rectangle(383.5f, 0.0f, 226.5f, 16.0f));
        colisoes.add(new Rectangle(608.0f, 0.0f, 287.0f, 16.0f));
        colisoes.add(new Rectangle(895.5f, 0.0f, 129.0f, 16.0f));

        // Borda da Direita
        colisoes.add(new Rectangle(1008.5f, 16.0f, 14.5f, 307.0f));

        // Borda superior
        colisoes.add(new Rectangle(777.5f, 320.5f, 245.5f, 16.0f));
        colisoes.add(new Rectangle(437.0f, 320.5f, 340.0f, 16.0f));
        colisoes.add(new Rectangle(368.0f, 320.0f, 67.5f, 15.5f));

        // Borda da Esquerda
        colisoes.add(new Rectangle(368.0f, 180.5f, 15.5f, 140.0f));
        colisoes.add(new Rectangle(367.5f, 176.5f, 16.0f, 3.5f));
        colisoes.add(new Rectangle(367.0f, 0.0f, 16.5f, 176.0f));
    }

    private void carregarColisoesInterior() {
        // Paredes da casa
        colisoes.add(new Rectangle(0,   0,   320, 32));   // inferior
        colisoes.add(new Rectangle(0,   224, 320, 16));   // superior
        colisoes.add(new Rectangle(0,   0,   16,  240));  // esquerda
        colisoes.add(new Rectangle(304, 0,   16,  240));  // direita

        // Zona de saída em frente à porta (centro inferior)
        zonaSaidaCasa = new Rectangle(144f, 32f, 32f, 16f);
    }

    private void entrarNaCasa() {
        mapa.dispose();
        mapa = new TmxMapLoader().load("Interior.tmx");
        mapaRenderer.setMap(mapa);

        TiledMapTileLayer camada = (TiledMapTileLayer) mapa.getLayers().get(0);
        mapWidth = camada.getWidth() * camada.getTileWidth();
        mapHeight = camada.getHeight() * camada.getTileHeight();

        colisoes.clear();
        carregarColisoesInterior();

        playerX = 165f;
        playerY = 70f;
        dentroDaCasa = true;
    }

    private void sairDaCasa() {
        mapa.dispose();
        mapa = new TmxMapLoader().load("Exterior.tmx");
        mapaRenderer.setMap(mapa);

        TiledMapTileLayer camada = (TiledMapTileLayer) mapa.getLayers().get(0);
        mapWidth = camada.getWidth() * camada.getTileWidth();
        mapHeight = camada.getHeight() * camada.getTileHeight();

        colisoes.clear();
        criarColisoesManuais();

        playerX = 719f;
        playerY = 145f;
        dentroDaCasa = false;
    }
    
    private Animation<TextureRegion> criarAnimacao(Texture sheet) {
        TextureRegion[][] tmp    = TextureRegion.split(sheet, FRAME_WIDTH, FRAME_HEIGHT);
        TextureRegion[]   frames = tmp[0];
        return new Animation<>(FRAME_DURATION, frames);
    }

    // Renderiza o jogo
    // ============================================================
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;
        movendo = false;

        // Guarda posição anterior para reverter se colidir
        float anteriorX = playerX;
        float anteriorY = playerY;

        // Movimentação WASD
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playerY += VELOCIDADE * delta;
            animAtual = animUp;
            movendo = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerY -= VELOCIDADE * delta;
            animAtual = animDown;
            movendo = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerX -= VELOCIDADE * delta;
            animAtual = animLeft;
            movendo = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerX += VELOCIDADE * delta;
            animAtual = animRight;
            movendo = true;
        }

        // Atualiza hitbox na nova posição
        // Centraliza a hitbox nos pés do personagem
        hitboxPlayer.setPosition(
            playerX - HITBOX_W / 2f,
            playerY - HITBOX_H
        );

        // Verifica colisão com os objetos do mapa
        for (Rectangle obstaculo : colisoes) {
            if (hitboxPlayer.overlaps(obstaculo)) {
                // Reverte para a posição anterior se colidiu
                playerX = anteriorX;
                playerY = anteriorY;
                hitboxPlayer.setPosition(
                    playerX - HITBOX_W / 2f,
                    playerY - HITBOX_H
                );
                break;
            }
        }

        // Limites do mapa (não sai pela borda)
        playerX = Math.max(HITBOX_W / 2f, Math.min(playerX, mapWidth  - HITBOX_W / 2f));
        playerY = Math.max(HITBOX_H,      Math.min(playerY, mapHeight - HITBOX_H));

        // Verifica se pode sair ou entrar na casa
        boolean podeEntrar = !dentroDaCasa && hitboxPlayer.overlaps(zonaEntradaCasa);
        boolean podeSair = dentroDaCasa && hitboxPlayer.overlaps(zonaSaidaCasa);
        podeInteragir = podeEntrar || podeSair;

        if (podeEntrar && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            entrarNaCasa();
        }
        if (podeSair && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            sairDaCasa();
        }

        // Frame da animação
        TextureRegion frameAtual;
        if (movendo) {
            frameAtual = animAtual.getKeyFrame(stateTime, true);
        } else {
            frameAtual = animAtual.getKeyFrame(0);
            stateTime  = 0f;
        }

        // Câmera segue o personagem
        camera.position.set(playerX, playerY, 0);
        camera.update();

        // Renderiza mapa
        mapaRenderer.setView(camera);
        mapaRenderer.render();

        // ── Renderiza personagem ──
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
            float escala = 1f;
            batch.draw(
                frameAtual,
                playerX - (FRAME_WIDTH  * escala) / 2f,
                playerY - (FRAME_HEIGHT * escala) / 2f,
                FRAME_WIDTH  * escala,
                FRAME_HEIGHT * escala
            );
            // Exibe a posição do jogador para testes
            font.draw(batch, "X:" + (int)playerX + " Y:" + (int)playerY, playerX - 40, playerY + 30);

            if (podeInteragir) {
                font.draw(batch, "Precione E", playerX - 30, playerY + 45);
            }

        batch.end();

        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(Color.RED);
        // Desenha hitbox do jogador
        for (Rectangle obstaculo : colisoes) {
            shapes.rect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height);
        }
        shapes.setColor(Color.LIME);
        shapes.rect(hitboxPlayer.x, hitboxPlayer.y, hitboxPlayer.width, hitboxPlayer.height);
        shapes.end();
    }

    // Redimensiona Camera
    //=============================================================
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    //Libera recursos
    //=============================================================
    @Override
    public void dispose() {
        batch.dispose();
        mapa.dispose();
        mapaRenderer.dispose();
        sheetDown.dispose();
        sheetUp.dispose();
        sheetLeft.dispose();
        sheetRight.dispose();
        shapes.dispose();
    }

    @Override public void pause()  {}
    @Override public void resume() {}
    @Override public void hide()   {}
}
