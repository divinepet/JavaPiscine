package entities;

public class RendererStandardImpl implements Renderer {
    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderMsg(String msg) {
        System.out.println(preProcessor.preProcessorMsg(msg));
    }
}
