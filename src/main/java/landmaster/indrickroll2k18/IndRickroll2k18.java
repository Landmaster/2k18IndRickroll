package landmaster.indrickroll2k18;

import javafx.animation.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.util.*;

public class IndRickroll2k18 extends Application {
	public static int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane stack = new StackPane();
		
		MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/Rick Astley - Never Gonna Give You Up (Video)-dQw4w9WgXcQ.mp4").toExternalForm()));
		MediaView mediaView = new MediaView(player);
		player.play();
		player.setOnEndOfMedia(() -> System.exit(0));
		
		stack.getChildren().add(mediaView);
		
		Canvas canvas = new Canvas(1280, 720);
		stack.getChildren().add(canvas);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25/1.9), new EventHandler<ActionEvent>() {
			int curIncr = 0;
			int curIdx = 0;
			int curFrame = 0;
			
			Paint[] paints = new Paint[] { Color.RED, Color.WHITE, Color.BLUE };
			
			@Override
			public void handle(ActionEvent event) {
				GraphicsContext ctx = canvas.getGraphicsContext2D();
				if (curIdx % 50 == 0) {
					curIdx = 0;
					ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
					while (gcd(++curIncr, 50) != 1)
						;
				}
				//System.out.println(curFrame);
				
				ctx.setFill(paints[curFrame % 3]);
				
				int centerPosX = 64 + 128 * ((curIdx % 50) % 10), centerPosY = 72 + 144 * ((curIdx % 50) / 10) ;
				
				ctx.fillOval(centerPosX - 40/2, centerPosY - 40/2, 40, 40);
				for (int i=0; i<5; ++i) {
					ctx.fillOval(centerPosX + 20*Math.sin(Math.PI * 2 * i / 5) - 20/2, centerPosY - 20*Math.cos(Math.PI * 2 * i / 5) - 20/2, 20, 20);
				}
				
				curIdx += curIncr;
				
				++curFrame;
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		Scene scene = new Scene(stack, 1280, 720);
		
		primaryStage.setTitle("Rickroll");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
