package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import test.vko.cn.ttapplication.commonutils.DpUtils;

/**
 * Created by JiaRH on 2015/11/24.
 */
public class FlowAndRefreshLay extends ViewGroup {

    // 行集合
    private List<Line> lineList = new ArrayList<Line>();
    // 当前行已用的宽度值
    private int usedWidth = 0;
    // 当前屏幕不可以超出的行数
    private int maxLine = 20;
    // 行对象
    private Line line;
    // 水平间距
    private int horizontolSpacing = 20;
    // wangyue@vko.cn
    // 行根行之间的间距
    private int verticalSpacing = 20;

    public FlowAndRefreshLay(Context context) {
        this(context, null);

    }

    public FlowAndRefreshLay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public FlowAndRefreshLay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (DpUtils.ScreenWidth(context) < 960) {
            horizontolSpacing = 10;
            verticalSpacing = 10;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 指定flowerlayout的位置
        int left = getPaddingLeft();
        int top = getPaddingTop();

        // 定义每一行的位置
        for (int i = 0; i < lineList.size(); i++) {
            Line line = lineList.get(i);
            line.layoutChild(left, top);
            top += line.lineHeight + verticalSpacing;
        }
    }

    // 测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 先将当前自定义控件的可用宽度获取出来
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 获取水平方向上的可用宽度
        int availableWidth = widthSize - getPaddingLeft() - getPaddingRight();

        // 获取所有的内部的TextView让其遵循定义出来的规则
        // 获取所有的textView的总个数
        int count = getChildCount();

        // 归位操作
        restoreLine();

        for (int i = 0; i < count; i++) {
            // 根据索引获取当前控件内部的view对象
            View childView = getChildAt(i);

            // 跳出此次循环进行下一次
            if (childView.getVisibility() == GONE) {
                continue;
            }

            // 定义每一个子节点的宽高值,控件的宽高值是由模式集合大小决定的

            // 子控件的宽度不可以去超过夫控件的宽度值

            // 夫控件的模式如果为精确,子控件就必须为至多(最多不能超过夫控件的宽度高度值),如果夫控件的模式不是至多,子控件模式和夫控件保持一致
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    availableWidth,
                    widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST
                            : widthMode);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    heightSize,
                    heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST
                            : heightMode);
            // 子控件按照定义的模式去做相应的绘画
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            // 获取子控件侧拉过后的宽度值
            usedWidth += childView.getMeasuredWidth();

            if (line == null) {
                line = new Line();
            }

            // 如果现在添加进来的控件的宽度超出可用宽度,则需要换行,否则直接添加在当前的行
            if (usedWidth < availableWidth) {
                // 加入当前行
                line.addView(childView);
                // 如果当前添加进来的view对应的宽度加上水平间距会超过可用宽度的时候,需要去做换行操作(创建一个新的行对象),
                usedWidth += horizontolSpacing;
                if (usedWidth > availableWidth) {
                    // 换行逻辑(换行的同时,添加当前childView后续view对象)
                    if (!newLine()) {
                        // 不是因为创建行对象返回false,跳出for循环而是,代码顺序执行完毕跳出循环
                        break;
                    }
                }
            } else {
                // 换行,(当前行的可用宽度usedWidth添加上对应的childView宽度后,宽度要大于可用宽度)
                if (line.getChildViewCount() == 0) {
                    // 当前行没有内部的view
                    // 即使当前行对应的view要超长,也需要将其加入
                    line.addView(childView);
                    // 换行操作
                    if (!newLine()) {
                        // 不是因为创建行对象返回false,跳出for循环而是,代码顺序执行完毕跳出循环
                        break;
                    }
                } else {
                    // 换行操作
                    if (!newLine()) {
                        // 不是因为创建行对象返回false,跳出for循环而是,代码顺序执行完毕跳出循环
                        break;
                    }
                    // 将超长的view添加到新的行中去
                    line.addView(childView);
                    // 将已用空间添加到当前的全局使用水平空间变量中去
                    usedWidth += childView.getMeasuredWidth()
                            + horizontolSpacing;
                }
            }
        }
        // 添加行对象到集合中的处理
        if (line != null && line.getChildViewCount() > 0
                && !lineList.contains(line)) {
            lineList.add(line);
        }
        // 当前FlowerLayout的宽高定义
        // 宽度获取
        // 高度的获取,所有的行的高度进行叠加+行根行间距
        int flowerLayoutHeight = 0;
        int lineCount = lineList.size();
        if (lineCount > 0) {
            for (int i = 0; i < lineCount; i++) {
                Line l = lineList.get(i);
                flowerLayoutHeight += l.lineHeight;
            }
//			flowerLayoutHeight += lineList.get(0).lineHeight * 5;
            flowerLayoutHeight = flowerLayoutHeight + (lineCount - 1)
                    * verticalSpacing;
        }
//		int buttom = height / 10;
//		System.out.println("flowerLayoutHeight=" + flowerLayoutHeight +" lineHeight="+lineList.get(0).lineHeight);
        // 获取滤掉宽高模式后的精确值将其传递给当前方法
        setMeasuredDimension(widthSize,
                resolveSize(flowerLayoutHeight, heightMode));
    }

    private void restoreLine() {
        // 行集合有行则需要清空
        lineList.clear();
        // 已使用的宽度归0
        usedWidth = 0;
        // 预设行对象的操作
        line = new Line();
    }

    private boolean newLine() {
        // 维护一个行的集合,用于添加已经放上控件的行对象
        lineList.add(line);
        if (lineList.size() < maxLine) {
            line = new Line();
            // 当前行已使用的宽度得去归位
            usedWidth = 0;
            return true;
        }
        return false;
    }

    class Line {
        // 维护当前行的高度值(FlowerLayout需要去指定绘画的高度,需要将多个行高度值进行叠加)
        private int lineHeight;
        // 维护当前行内view总宽度的一个变量
        // (flowerLayout宽度-当前行view的总宽度-当前行view的间距 = 水平的留白区域(平均分配当前行给每一个view))
        private int viewWidthCount;

        // 行中维护一个放置view的集合
        private List<View> viewList = new ArrayList<View>();

        public void addView(View view) {
            viewList.add(view);
            // 获取行对象的宽度高度
            int childWidthSize = view.getMeasuredWidth();
            int childHeightSize = view.getMeasuredHeight();

            // 宽度的叠加最终值
            viewWidthCount += childWidthSize;
            // 高度的获取(行高度是内部控件最高view的高度值)
            lineHeight = lineHeight < childHeightSize ? childHeightSize
                    : lineHeight;
        }

        // 行对象

        public int getChildViewCount() {
            return viewList.size();
        }

        // 留白区域的分配,根据原有左上角位置去做叠加操作
        public void layoutChild(int l, int t) {
            int left = l;
            int top = t;
            // 计算水平的留白区域
            int availableWidth = getMeasuredWidth() - getPaddingRight()
                    - getPaddingLeft();
            // 水平留白区域
            int surplusTotalWidth = availableWidth - viewWidthCount
                    - (getChildViewCount() - 1) * horizontolSpacing;
            if (surplusTotalWidth > 0) {
                // 分配水平留白区域,原有控件的水平宽度基础上加上分配到的水平剩余空间
                int surplusWidth = (int) (surplusTotalWidth
                        / getChildViewCount() + 0.5);
                // 加到控件原有宽度上去
                for (int i = 0; i < getChildViewCount(); i++) {
                    View childView = viewList.get(i);

                    int childViewWidth = childView.getMeasuredWidth()
                            + surplusWidth;
                    // 控件高度处理(控件最高高度-当前控件高度 = 竖直留白区域)
                    int surplusHeight = lineHeight
                            - childView.getMeasuredHeight();
                    int surplusHalfHeight = (int) (surplusHeight / 2 + 0.5);

                    // 将添加上水平留白区域的宽度设置给控件规则
                    childView.getLayoutParams().width = childViewWidth;

                    // 绘画过程必须精确指明宽高
                    int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                            childViewWidth, MeasureSpec.EXACTLY);
                    int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                            childView.getMeasuredHeight(), MeasureSpec.EXACTLY);

                    childView.measure(childWidthMeasureSpec,
                            childHeightMeasureSpec);

                    // onMeasure测量 onlayout放置位置,布局 onDraw()绘画
                    // (左上)(右下)
                    // 控件没有left+childViewWidth+horizontolSpacing这么宽,但是占有这么大的区域
                    childView.layout(left, top + surplusHalfHeight, left
                            + childViewWidth, top + surplusHalfHeight
                            + childView.getMeasuredHeight());
                    left += childViewWidth + horizontolSpacing;
                }
            } else {
                // 当前行就一个view对象,所以没有留白区域
                if (getChildViewCount() == 1) {
                    // 获取当前行唯一的控件去做onlayout操作
                    View view = viewList.get(0);
                    view.layout(left, top, left + view.getMeasuredWidth(), top
                            + view.getMeasuredHeight());
                }
            }
        }
    }
}

