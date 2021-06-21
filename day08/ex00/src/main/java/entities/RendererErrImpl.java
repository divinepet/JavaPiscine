package entities;

public class RendererErrImpl implements Renderer {
    PreProcessor preProcessor;

    RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderMsg(String msg) {
        System.err.println(preProcessor.preProcessorMsg(msg));
    }
}
