package com.ant.app.utils;

import com.ant.app.entity.tree.TreNode;
import com.ant.app.model.SaleUser;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lchunlei
 * @date 2018/12/25
 */
public class TreeUtil {

    private static final String ME = "我";
    private final static String treeStr = "00000000000000000000000000000000";

    //获取指定层数的下子总数
    public static List<String> treeList(Integer weiShu){
        String realTreeStr = treeStr.substring(0,weiShu);
        List<String> realTrees = new ArrayList();
        realTrees.add(realTreeStr);
        for(int i=0;i<weiShu;i++){
            List<String> nowRealTrees = new ArrayList();
            nowRealTrees.addAll(realTrees);
            for(String str:nowRealTrees){
                realTrees.add(tiHuan(i,str,"1"));
            }
        }
        return realTrees;
    }

    public static TreNode init(List<SaleUser> users){
        int floor = getFloor(users.size());
        System.out.println("总层数--》"+floor);
        List<TreNode> nodes = reNodes(users);
        TreNode treNode = new TreNode(ME);
        for(TreNode n:nodes){
            int index = nodes.indexOf(n);
            if(index>13){
                break;
            }
            addNode(n,treNode);
        }
        return treNode;
    }

    private static void addNode(TreNode node,TreNode supNode){
        //第二层
        if(supNode.getChildren().size()==2){
            //第三层
            if(supNode.getChildren().get(0).getChildren().isEmpty()){
                supNode.getChildren().get(0).getChildren().add(node);
            }else if(supNode.getChildren().get(1).getChildren().isEmpty()){
                supNode.getChildren().get(1).getChildren().add(node);
            }else if(supNode.getChildren().get(0).getChildren().size()==1){
                supNode.getChildren().get(0).getChildren().add(node);
            }else if(supNode.getChildren().get(1).getChildren().size()==1){
                supNode.getChildren().get(1).getChildren().add(node);
            }else {
                //第四层
                if(supNode.getChildren().get(0).getChildren().get(0).getChildren().isEmpty()){
                    supNode.getChildren().get(0).getChildren().get(0).getChildren().add(node);
                }else if(supNode.getChildren().get(0).getChildren().get(1).getChildren().isEmpty()){
                    supNode.getChildren().get(0).getChildren().get(1).getChildren().add(node);
                }else if(supNode.getChildren().get(1).getChildren().get(0).getChildren().isEmpty()){
                    supNode.getChildren().get(1).getChildren().get(0).getChildren().add(node);
                }else if(supNode.getChildren().get(1).getChildren().get(1).getChildren().isEmpty()){
                    supNode.getChildren().get(1).getChildren().get(1).getChildren().add(node);
                }else if(supNode.getChildren().get(0).getChildren().get(0).getChildren().size()==1){
                    supNode.getChildren().get(0).getChildren().get(0).getChildren().add(node);
                }else if(supNode.getChildren().get(0).getChildren().get(1).getChildren().size()==1){
                    supNode.getChildren().get(0).getChildren().get(1).getChildren().add(node);
                }else if(supNode.getChildren().get(1).getChildren().get(0).getChildren().size()==1){
                    supNode.getChildren().get(1).getChildren().get(0).getChildren().add(node);
                }else if(supNode.getChildren().get(1).getChildren().get(1).getChildren().size()==1){
                    supNode.getChildren().get(1).getChildren().get(1).getChildren().add(node);
                }
            }


        }else {
            //第一层
            supNode.getChildren().add(node);
        }

    }
    //重新封装节点
    private static TreNode reNodes(SaleUser user){
        TreNode n = new TreNode(user.getUserId().toString());
        return n;
    }

    //重新封装节点
    private static List<TreNode> reNodes(List<SaleUser> users){
        List<TreNode> nodes = new ArrayList();
        for (SaleUser u:users){
            TreNode n = new TreNode(u.getUserId().toString());
            nodes.add(n);
        }
        return nodes;
    }
    //判断判断最后一层个数
    private static int getLastNum(int total){
        if (total<3){
            return total;
        }else if(total<7){
            return total-2;
        }else if(total<15){
            return total-2-4;
        }else if(total<31){
            return total-2-4-8;
        }else if(total<63){
            return total-2-4-8-16;
        }else if(total<127){
            return total-2-4-8-16-32;
        }else if(total<255){
            return total-2-4-8-16-32-64;
        }else if(total<511){
            return total-2-4-8-16-32-64-128;
        }else if(total<1023){
            return total-2-4-8-16-32-64-128-256;
        }else {
            return 0;
        }
    }
    //判断每层第一个序号
    private static int getfirstIndex(int floor){
        switch (floor){
            case 1:return 0;
            case 2:return 2;
            case 3:return 6;
            case 4:return 14;
            case 5:return 30;
            case 6:return 62;
            case 7:return 126;
            case 8:return 254;
            case 9:return 510;
            case 10:return 1022;
            default:return 0;
        }
    }
    //判断每层满树个数
    private static int getNumOnFloor(int floor){
        switch (floor){
            case 1:return 2;
            case 2:return 4;
            case 3:return 8;
            case 4:return 16;
            case 5:return 32;
            case 6:return 64;
            case 7:return 128;
            case 8:return 256;
            case 9:return 512;
            case 10:return 1024;
            default:return 0;
        }
    }

    //判断层数
    private static int getFloor(int total){
        if (total<3){
           return 1;
        }else if(total<7){
            return 2;
        }else if(total<15){
            return 3;
        }else if(total<31){
            return 4;
        }else if(total<63){
            return 5;
        }else if(total<127){
            return 6;
        }else if(total<255){
            return 7;
        }else if(total<511){
            return 8;
        }else if(total<1023){
            return 9;
        }else {
            return 0;
        }
    }

    //替换字符串指定位置的字符
    private static String tiHuan(int i,String str,String replaceStr){
        return str.substring(0,i)+replaceStr+str.substring(i+1);
    }

}
